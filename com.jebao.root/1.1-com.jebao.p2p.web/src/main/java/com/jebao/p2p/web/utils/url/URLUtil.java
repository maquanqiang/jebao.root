package com.jebao.p2p.web.utils.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/10/19.
 */
public class URLUtil {
    private static final String ENCODING="utf-8";
    public static String encode(String url) {
        try {
            return URLEncoder.encode(url, ENCODING);
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public static String decode(String url) {
        try {
            return URLDecoder.decode(url, ENCODING);
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }
}
