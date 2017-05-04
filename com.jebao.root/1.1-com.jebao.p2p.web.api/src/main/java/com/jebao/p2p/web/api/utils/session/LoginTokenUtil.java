package com.jebao.p2p.web.api.utils.session;

import com.jebao.common.cache.redis.sharded.ShardedRedisUtil;
import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.p2p.web.api.utils.constants.Constants;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * Created by Administrator on 2016/10/24.
 */
public class LoginTokenUtil {

    public static String setLoginToken(CurrentUser currentUser)
    {
        String uuid= UUID.randomUUID().toString();
        String loginTokenFullName=getLoginTokenFullName(uuid);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        redisUtil.set(loginTokenFullName,currentUserToJson(currentUser),"NX","EX",getLoginTokenExpireTime());
        return uuid;
    }

    public static CurrentUser getLoginToken(String uuid)
    {
        String loginTokenFullName=getLoginTokenFullName(uuid);
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        String currentUserJson= redisUtil.get(loginTokenFullName);
        if(StringUtils.isBlank(currentUserJson))return null;
        return jsonToCurrentUser(currentUserJson);
    }
    private static String getLoginTokenFullName(String uuid)
    {
        return  Constants.LOGIN_SESSION_TOKEN_CACHE_NAME+uuid;
    }
    private static int getLoginTokenExpireTime()
    {
        return Constants.LOGIN_SESSION_TOKEN_EXPIRE_TIME;
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