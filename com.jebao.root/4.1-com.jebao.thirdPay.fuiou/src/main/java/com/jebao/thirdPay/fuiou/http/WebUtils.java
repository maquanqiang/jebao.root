package com.jebao.thirdPay.fuiou.http;

public class WebUtils {

    public static String sendHttp(String url, Object parameters) throws Exception  {
        String outStr="";
        try {
            String charSet="UTF-8";
            String timeOut ="30000";//自行配置
            outStr = HttpClientHelper.doHttp(url,charSet,parameters, timeOut);
            if(outStr==null){
                throw new Exception("请求接口失败!");
            }
            //System.out.println("outStr="+outStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("请求接口失败!");
        }
        return outStr;
    }
}
