package com.jebao.common.utils.sms;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/4.
 */
public class SmsSendUtil {
    /**
     * 方法名称：sendVerifyCode
     * 功    能：发送验证码
     * 参    数：phone,verifyCode(手机号，验证码)
     * 返 回 值：
     */
    public static String sendVerifyCode(String phone,String verifyCode){
        String content  = "";
        if(phone != null && !"".equals(phone)){
            // 获得短信发送模板
            String temp = SmsUtil.smsVerifyCodeTemp;
            if(temp!=null && !"".equals(temp)){
                content = temp.replace("${code}",verifyCode)
                        .replace("${phone}", SmsUtil.serviceTel)
                        .replace("${subject}", SmsUtil.subject);
            }
        }
        sendMsg(phone, content);
        return "";
    }

    /**
     * 发送投资成功短信
     * @param phone
     * @param date
     * @param voucher
     * @param investMoney
     * @return
     */
    public static String sendInvestMsg(String phone, Date date, BigDecimal voucher, BigDecimal investMoney){
        String content = "";
        String temp = SmsUtil.smsBidPlanTemp;
        if(phone != null && !"".equals(phone)){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(voucher!=null){
                temp = SmsUtil.smsVoucherTem;
            }
            content = temp.replace("${time}", sdf.format(date))
                    .replace("${investMoney}", investMoney.setScale(2, BigDecimal.ROUND_DOWN).toString())
                    .replace("${money}", voucher == null ? "" : voucher.toString())
                    .replace("${phone}", SmsUtil.serviceTel)
                    .replace("${subject}", SmsUtil.subject);
        }
        sendMsg(phone, content);
        return "";
    }




    /**
     * 方法名称：sendMsg
     * 功    能：发送短信
     * 参    数：phone,content(手机号，短信内容)
     * 返 回 值：
     */
    public static String sendMsg(String phone, String content) {
        try {
            content = java.net.URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SmsUtil.mdsmssend(phone, content, "", "", "", "");
        return null;
    }


    /**
     * 还款成功短信提示
     */

    public static String sendRepaySms(String phone, Date date, BigDecimal total, Integer fundType, String name){
        String content = "";
        String temp = SmsUtil.smsRepayTemp;
        if(phone != null && !"".equals(phone)){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            content = temp.replace("${time}", sdf.format(date))
                    .replace("${total}", total.setScale(2, BigDecimal.ROUND_DOWN).toString())
                    .replace("${fundType}", fundType == 1? "本金":"利息")
                    .replace("${planName}",name)
                    .replace("${phone}", SmsUtil.serviceTel);
        }
        sendMsg(phone, content);
        return "";
    }

    /**
     *
     * @param phone
     * @param total
     * @return
     */
    public static String sendVoucherRemindSms(String phone, String days, BigDecimal total){
        String content = "";
        String temp = SmsUtil.smsVoucherRemindTemp;
        if(phone != null && !"".equals(phone)){

            content = temp.replace("${days}", days)
                    .replace("${money}", total.setScale(2, BigDecimal.ROUND_DOWN).toString())
                    .replace("${phone}", SmsUtil.serviceTel);
        }
        sendMsg(phone, content);
        return "";
    }
}
