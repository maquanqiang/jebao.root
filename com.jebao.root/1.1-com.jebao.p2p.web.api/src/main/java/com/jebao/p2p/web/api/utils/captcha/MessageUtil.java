package com.jebao.p2p.web.api.utils.captcha;

import com.jebao.common.cache.redis.sharded.ShardedRedisUtil;
import com.jebao.common.utils.sms.SmsSendUtil;
import com.jebao.common.utils.validation.ValidatorUtil;
import com.jebao.p2p.web.api.responseModel.base.JsonResult;
import com.jebao.p2p.web.api.responseModel.base.JsonResultError;
import com.jebao.p2p.web.api.responseModel.base.JsonResultOk;
import com.jebao.p2p.web.api.utils.constants.Constants;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by Jack on 2016/12/9.
 */
public class MessageUtil {
    private final String VERIFY_CODES = "0123456789";
    private final int EXPIRE_TIME_SECONDS=60 * 10; //10分钟有效期
    private final int MOBILE_MAX_SEND_NUMBER = 10; //单个手机号日最大发送次数
    private final int IP_MAX_SEND_NUMBER=20; //单个ip地址日最大发送次数
    private final int SEND_INTERVAL_SECONDS = 89; //短信发送间隔限制秒数，预留一秒给网络传输、服务端处理 时间
    /**
     * 发送短信验证码
     * @param mobile 目标手机号码
     * @return jsonResult
     */
    public JsonResult sendMessageVerifyCode(String mobile, String ip){
        if (mobile == null || ! new ValidatorUtil().isMobile(mobile)){
            return new JsonResultError("手机号码输入错误");
        }
        //校验发送次数
        MessageRedisValue mobileRedisValue = getRedis(mobile);//获取该手机号的 redis 内容
        //Period.between(date1,date2);
        if (mobileRedisValue != null && mobileRedisValue.getLastSendTime().toLocalDate().isEqual(LocalDate.now())){ //同一天的，检查发送次数
            if (mobileRedisValue.getSendNumber() >= MOBILE_MAX_SEND_NUMBER){ //超出单个手机号单日最大发送次数
                return new JsonResultError("手机短信发送过于频繁");
            }
            if (LocalDateTime.now().minusSeconds(SEND_INTERVAL_SECONDS).isBefore(mobileRedisValue.getLastSendTime())){ //验证发送间隔
                return new JsonResultError("操作太快了，请先休息下");
            }
        }
        MessageRedisValue ipRedisValue = getRedis(ip); // ip限制
        if (ipRedisValue != null && ipRedisValue.getLastSendTime().toLocalDate().isEqual(LocalDate.now())){
            if (ipRedisValue.getSendNumber() >= IP_MAX_SEND_NUMBER){
                return new JsonResultError("IP短信发送过于频繁");
            }
        }

        String verifyCode = generateVerifyCode(6); //生成短信验证码
        if (Constants.IsTest){
            verifyCode="0000"; //测试环境默认短信验证码： 0000
        }else{
            SmsSendUtil.sendVerifyCode(mobile,verifyCode); //发送短信验证码
        }

        //设置/更新 redis
        if (mobileRedisValue == null){
            mobileRedisValue = new MessageRedisValue();
        }
        mobileRedisValue.setVerifyCode(verifyCode);
        mobileRedisValue.setSendNumber(mobileRedisValue.getSendNumber() + 1);
        mobileRedisValue.setLastSendTime(LocalDateTime.now());

        setRedis(mobile,mobileRedisValue); // 设置手机号的发送情况 在 redis

        if (ipRedisValue == null){
            ipRedisValue = new MessageRedisValue();
        }
        ipRedisValue.setVerifyCode(verifyCode);
        ipRedisValue.setSendNumber(ipRedisValue.getSendNumber() + 1);
        ipRedisValue.setLastSendTime(LocalDateTime.now());

        setRedis(ip,ipRedisValue); // 设置ip的发送情况 在 redis

        return new JsonResultOk("短信已发送");
    }
    /**
     * 获取短信验证码
     * @param simpleKey redisKey
     * @return 短信验证码
     */
    public MessageRedisValue getRedis(String simpleKey){
        String key = getRedisKey(simpleKey);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        return redisUtil.get(key,MessageRedisValue.class);
    }
    private void setRedis(String simpleKey,MessageRedisValue value)
    {
        String key=getRedisKey(simpleKey);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        redisUtil.setex(key,EXPIRE_TIME_SECONDS,value);
    }

    /**
     * 获取短信验证码
     * @param mobile 目标手机号
     * @return 短信验证码
     */
    public String getVerifyCode(String mobile){
        MessageRedisValue redisValue = getRedis(mobile);
        return redisValue == null ? "":redisValue.getVerifyCode();
    }
    /**
     * 校验短信验证码
     * @param mobile 目标手机号码
     * @param code 用户输入验证码
     * @return 是否正确
     */
    public boolean isValidCode(String mobile,String code){
        String redisVerifyCode = getVerifyCode(mobile);
        return !StringUtils.isBlank(redisVerifyCode) && redisVerifyCode.equalsIgnoreCase(code);
    }

    public String generateVerifyCode(int length){
        return generateVerifyCode(length,VERIFY_CODES);
    }
    public String generateVerifyCode(int length, String sources){
        if(StringUtils.isBlank(sources)){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }



    private String getRedisKey(String simpleKey)
    {
        return Constants.CAPTCHA_TOKEN_CACHE_NAME+simpleKey;
    }

}
