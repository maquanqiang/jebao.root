package com.jebao.thirdPay.fuiou.http;

import org.apache.commons.httpclient.NameValuePair;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2016/9/27.
 */
public class WebFormUtils {
    public static String createFormHtml(String httpUrl,Object parameters) throws Exception {
        if(httpUrl==null)
        {
            throw new Exception("httpUrl不能为空");
        }
        String formHtml=null;
        List<NameValuePair> nameValuePairList=objToParam(parameters);
        String paramHtml=getParamsHtml(nameValuePairList);
        StringBuffer sb=new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta charset=\"utf-8\" />");
        sb.append("<script type=\"text/javascript\">");
        sb.append("function redirectUrl() {");
        sb.append("document.form0.submit();");
        sb.append("}");
        sb.append("</script>");
        sb.append("</head>");
        sb.append("<body onload=\"redirectUrl()\">");
        sb.append("<form name=\"form0\" action=\""+httpUrl+"\" method=\"post\">");
        if(paramHtml!=null){
            sb.append(paramHtml);
        }
        sb.append("</form>");
        sb.append("</body>");
        sb.append("</html>");
        formHtml=sb.toString();
        return formHtml;
    }
    private static List<NameValuePair> objToParam(Object parameters) throws Exception {
        //组合请求参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Method[] ms = parameters.getClass().getMethods();
        for (int i = 0; i < ms.length; i++) {
            Method m = ms[i];
            String name = m.getName();
            if (name.startsWith("get")) {
                String param = name.substring(3, name.length());
                param = param.substring(0, 1).toLowerCase() + param.substring(1, param.length());
                if (param.equals("class")) {
                    continue;
                }
                Object value = "";
                try {
                    value = m.invoke(parameters, null);
                    NameValuePair nvp = new NameValuePair(param, value == null? "":value.toString());
                    list.add(nvp);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return list;
    }
    /**
     * 获取中间页面的form表单参数
     * @param params
     * @return
     */
    private static String getParamsHtml(List<NameValuePair> params) {
        // TODO Auto-generated method stub
        String htmlParams=null;
        try{
            if(params!=null){
                StringBuffer sb=new StringBuffer();
                for(int i = 0; i < params.size(); i++)
                {
                    NameValuePair entry= params.get(i);
                    Object key = entry.getName();
                    Object val = entry.getValue();
                    sb.append("<input type=\"hidden\" name='"+key.toString()+"\' value='"+val.toString()+"' />");
                }
                htmlParams=sb.toString();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return htmlParams;
    }

}
