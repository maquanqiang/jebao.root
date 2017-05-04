package com.jebao.p2p.web.api.utils.cached;

import com.jebao.common.utils.fastjson.FastJsonUtil;
import com.jebao.thirdPay.fuiou.util.MD5Util;

/**
 * Created by Administrator on 2017/1/17.
 */
public class CachedUtil {
    public static <T> String KeyMd5(T object){
        String val= FastJsonUtil.serialize(object);
        return MD5Util.encode(val,"UTF-8");
    }
    public static String KeyMd5(String val){
        return MD5Util.encode(val,"UTF-8");
    }
    public static <T> String KeySerialize(T object){
        String val= FastJsonUtil.serialize(object);
        return val;
    }
}
