package com.jebao.p2p.web.api.utils.http;

import com.jebao.jebaodb.entity.extEntity.EnumModel;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Jack on 2016/12/7.
 */
public class HttpUtil {
    /**
     * 获取客户端真实ip地址
     *
     * @param request HttpServletRequest
     * @return 客户端ip地址
     */
    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");

        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    //e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (!StringUtils.isBlank(ipAddress) && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
        return ipAddress;
    }

    /**
     * 获取请求所属平台
     * @param request httpRequest
     * @return 平台类型
     */
/*    public EnumModel.Platform getPlatform(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (!StringUtils.isBlank(userAgent)) {
            if (userAgent.indexOf("MicroMessenger") > -1) { //微信
                return EnumModel.Platform.weixin;
            } else if (userAgent.indexOf("Android") > -1) {
                return EnumModel.Platform.android;
            }else if (userAgent.indexOf("iPhone")>-1 || userAgent.indexOf("Mac OS X")>-1){
                return EnumModel.Platform.ios;
            }else if (userAgent.indexOf("Windows")>-1){
                return EnumModel.Platform.pc;
            }
        }
        return EnumModel.Platform.other;
    }*/

    /**
     * 获取请求所属平台
     * @param request httpRequest
     * @return 平台类型
     */
    public EnumModel.Platform getPlatform(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (!StringUtils.isBlank(userAgent)) {
            if (userAgent.indexOf("Android") > -1 || userAgent.indexOf("iPhone")>-1 || userAgent.indexOf("Mac OS X")>-1) {
                return EnumModel.Platform.mobile;
            }else if (userAgent.indexOf("Windows")>-1){
                return EnumModel.Platform.pc;
            }else{
                return EnumModel.Platform.other;
            }
        }
        return EnumModel.Platform.other;
    }

    /**
     * 获取请求所属平台分类
     * @param request httpRequest
     * @return 平台类型
     */
    public EnumModel.PlatformType getPlatformType(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (!StringUtils.isBlank(userAgent)) {
            if (userAgent.indexOf("MicroMessenger") > -1) { //微信
                return EnumModel.PlatformType.weixin;
            } else if (userAgent.indexOf("Android") > -1) {
                return EnumModel.PlatformType.android;
            }else if (userAgent.indexOf("iPhone")>-1){
                return EnumModel.PlatformType.iphone;
            }else if(userAgent.indexOf("Mac OS X")>-1){
                return EnumModel.PlatformType.mac;
            }else if (userAgent.indexOf("Windows")>-1){
                return EnumModel.PlatformType.pc;
            }
        }
        return EnumModel.PlatformType.other;
    }

    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public String sendHttpGet(String url){
        HttpClient httpClient = new HttpClient();
        httpClient.setConnectionTimeout(200000);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        GetMethod getMethod = new GetMethod(url);

        //get请求返回结果
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpURLConnection.HTTP_OK){
                return getMethod.getResponseBodyAsString();
            }

        } catch (Exception e) {

        }
        return "";
    }


}
