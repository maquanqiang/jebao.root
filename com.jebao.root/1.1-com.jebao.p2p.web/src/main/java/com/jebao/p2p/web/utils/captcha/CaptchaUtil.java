package com.jebao.p2p.web.utils.captcha;

import com.jebao.common.cache.redis.sharded.ShardedRedisUtil;
import com.jebao.p2p.web.utils.constants.Constants;
import com.jebao.p2p.web.utils.cookie.CookieUtil;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><b>VerifyCodeUtils Description:</b> (验证码生成)</p>
 * <b>DATE:</b> 2016年6月2日 下午3:53:34
 * JAVA 验证码生成
 * http://www.cnblogs.com/jianlun/articles/5553452.html
 *
 */
public class CaptchaUtil{

   public static void setCaptchaToken(String code,HttpServletRequest request, HttpServletResponse response)
   {
       String uuid= UUID.randomUUID().toString();
       CookieUtil cookieUtil=new CookieUtil(request,response,getCaptchaTokenExpireTime());
       cookieUtil.addCookie(getCaptchaTokenCookieName(),uuid);
       setCaptchaTokenInRedis(uuid,code);
   }
    public static String getCaptchaToken(HttpServletRequest request, HttpServletResponse response)
    {
        CookieUtil cookieUtil=new CookieUtil(request,response,getCaptchaTokenExpireTime());
        String uuid= cookieUtil.getCookieValue(getCaptchaTokenCookieName());
        if(StringUtils.isBlank(uuid))return null;
        return getCaptchaTokenInRedis(uuid);
    }


    private static int getCaptchaTokenExpireTime()
    {
       return Constants.CAPTCHA_TOKEN_EXPIRE_TIME;
    }
    private static String getCaptchaTokenCookieName()
    {
        return Constants.CAPTCHA_TOKEN_COOKIE_NAME;
    }
    private static void setCaptchaTokenInRedis(String uuid,String code)
    {
        String key=getCaptchaTokenCacheFullName(uuid);
        String value=code;
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        String result=redisUtil.set(key, value,"XX","EX",getCaptchaTokenExpireTime());
        if(result==null)
        {
            redisUtil.set(key, value,"NX","EX",getCaptchaTokenExpireTime());
        }
    }
    private static String getCaptchaTokenInRedis(String uuid) {
        String key=getCaptchaTokenCacheFullName(uuid);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        return redisUtil.get(key);
    }
    private static String getCaptchaTokenCacheFullName(String uuid)
    {
        return Constants.CAPTCHA_TOKEN_CACHE_NAME+uuid;
    }
}
