package com.jebao.erp.web.utils.session;

import com.jebao.common.cache.redis.sharded.ShardedRedisUtil;
import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.erp.web.utils.constants.Constants;
import com.jebao.erp.web.utils.cookie.CookieUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Administrator on 2016/10/12.
 */
public class LoginSessionUtil {

    /**
     * 获取是否登录
     *
     * @return
     */
    public static boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
        CurrentUser currentUser=User(request, response);
        boolean isOk=currentUser != null;
        if(isOk)
        {
            Refresh(currentUser,request,response);
        }
        return isOk;
    }

    /**
     * 获取设置登录
     *
     * @return
     */
    public static void setLogin(CurrentUser currentUser, HttpServletRequest request, HttpServletResponse response) {
        CookieUtil cookieUtil = new CookieUtil(request, response, getLoginSessionExpireTime());
        cookieUtil.deleteAllCookie();
        //
        String uuid= UUID.randomUUID().toString();
        currentUser.setUUID(uuid);
        String loginSessionKey= getLoginSessionKey(currentUser);;
        String loginSessionCookieVal=currentUserToJson(currentUser);
        String loginSessionCookieName = getLoginSessionCookieName();
        cookieUtil.addCookie(loginSessionCookieName, loginSessionCookieVal);
        SetLoginSessionInRedis(loginSessionKey,currentUser);
    }
    /**
     *  loginSession保存到Redis
     */
    private static void SetLoginSessionInRedis(String loginSessionKey,CurrentUser currentUser)
    {
        String key=getLoginSessionFullName(loginSessionKey);
        String value=currentUserToJson(currentUser);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        String result=redisUtil.set(key, value,"XX","EX",getLoginSessionExpireTime());
        if(result==null)
        {
            redisUtil.set(key, value,"NX","EX",getLoginSessionExpireTime());
        }
    }

    /**
     * 获取退出
     *
     * @return
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil cookieUtil = new CookieUtil(request, response,getLoginSessionExpireTime());
        String loginSessionKeyCookieValue=cookieUtil.getCookieValue(getLoginSessionCookieName());
        if (StringUtils.isNotBlank(loginSessionKeyCookieValue)) {
            CurrentUser currentUser =jsonToCurrentUser(loginSessionKeyCookieValue);
            String loginSessionKey =getLoginSessionKey(currentUser);
            String key=getLoginSessionFullName(loginSessionKey);
            ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
            redisUtil.del(key);
        }
        cookieUtil.deleteAllCookie();
    }
    /*
    *   获得当前用户
    * **/
    public static CurrentUser User(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil cookieUtil = new CookieUtil(request, response, getLoginSessionExpireTime());
        CurrentUser currentUser = null;
        String loginSessionKeyCookieValue=cookieUtil.getCookieValue(getLoginSessionCookieName());
        if (StringUtils.isNotBlank(loginSessionKeyCookieValue)) {
            CurrentUser currentUserInCookie =jsonToCurrentUser(loginSessionKeyCookieValue);
            String loginSessionKey =getLoginSessionKey(currentUserInCookie);
            String key=getLoginSessionFullName(loginSessionKey);
            ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
            String loginSessionValue= redisUtil.get(key);
            if(StringUtils.isBlank(loginSessionValue))
            {
                return null;
            }
            currentUser=jsonToCurrentUser(loginSessionValue);
            //相同账号是否请允许多用户登录
            //if(!currentUser.getUUID().equals(currentUserInCookie.getUUID()))return null;
        }
        return currentUser;
    }
    /**
     * //刷新Redis中用户的登录Session
     * */
    public static void Refresh(CurrentUser currentUser,HttpServletRequest request, HttpServletResponse response)
    {
        String loginSessionKey= getLoginSessionKey(currentUser);;
        String loginSessionCookieVal=currentUserToJson(currentUser);
        String loginSessionCookieName = getLoginSessionCookieName();
        setUserLoginSessionInCookie(loginSessionCookieName,loginSessionCookieVal,request,response);
        SetLoginSessionInRedis(loginSessionKey,currentUser);
    }
    private static void setUserLoginSessionInCookie(String loginSessionCookieName,String loginSessionCookieVal,HttpServletRequest request, HttpServletResponse response )
    {
        CookieUtil cookieUtil = new CookieUtil(request, response, getLoginSessionExpireTime());
        cookieUtil.addCookie( loginSessionCookieName, loginSessionCookieVal);
    }

    private static String getLoginSessionKey(CurrentUser currentUser)
    {
        int id=currentUser.getId();
        return  Integer.toString(id);
    }
    private static String getLoginSessionFullName(String loginSessionKey)
    {
        return Constants.LOGIN_SESSION_CACHE_NAME+loginSessionKey;
    }
    private static int getLoginSessionExpireTime()
    {
        return Constants.LOGIN_SESSION_EXPIRE_TIME;
    }
    private static String getLoginSessionCookieName()
    {
        return Constants.LOGIN_SESSION_COOKIE_NAME;
    }
    private static String currentUserToJson(CurrentUser currentUser)
    {
        return FastJsonUtil.serialize(currentUser);
    }
    private static CurrentUser jsonToCurrentUser(String json)
    {
        return FastJsonUtil.deserialize(json,CurrentUser.class);
    }
}
