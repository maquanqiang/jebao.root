package com.jebao.common.utils.sms;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/4.
 */
public class SmsUtil {
    private static String thirdUrl = "http://sdk2.entinfo.cn:8061/webservice.asmx";//第三方短信通道接口地址
   // public static String testVerifyCode = "";//是否为测试环境验证码 111111
    private static String sn = "SDK-BBX-010-23608";//商户帐号
    private static String pwd = "9dA-88b8";//商户密码
    public static String smsVerifyCodeTemp = "您的验证码：${code}，请在页面中输入以完成验证。如有问题请致电${phone}客服热线或联系在线客服。【${subject}】";
    public static String smsBidTemp = "您已成功投标项目:${projName}${code}元，有问题请致电${phone4}【${subject}】";
    public static String subject = "金额宝";
    public static String serviceTel = "4008-707-701";//客服电话
    public static String smsRegisteredTemp = "尊敬的会员，欢迎您加入${subject}，您的初始密码为${code}。为了确保您的帐户安全以及投资身份，请您尽快修改初始密码并开通第三方托管。如您在网站操作中有任何问题请致电${phone}客服热线或联系在线客服，我们将为您提供最贴心的服务。【${subject}】";
    public static String smsRecommendRegTemp = "启奏陛下，您钦点的爱臣已成功入驻${subject}，特送上${money}元红包感谢陛下的举荐之恩。红包已放置您的私人账户。微臣随时听候传旨，如有疑问请致电${phone}【${subject}】";
    public static String smsBidPlanTemp = "尊敬的用户，您好！您在${time}成功投资${investMoney}元。${subject}祝您生活愉快！如有疑问请致电${phone}【${subject}】";
    public static String smsVoucherTem = "尊敬的用户，您好！您在${time}成功投资${investMoney}元，可获得${money}元红包返现。红包已放置到您的账户，${subject}祝您生活愉快！如有疑问请致电${phone}【${subject}】";
    public static String smsRepayTemp = "您于${time}收到${planName}项目的本次${fundType}共计￥${total}元,如有疑问请咨询：${phone}";
    public static String smsVoucherRemindTemp = "尊敬的用户，您的${money}元红包即将过期（有效期至${outDate}），请尽快使用，来赚取收益吧。详情请登录官网m.jebao.net";
    /**
     * 方法名称：getMD5
     * 功    能：字符串MD5加密
     * 参    数：待转换字符串
     * 返 回 值：加密之后字符串
     */
    private static String getMD5(String sourceStr) throws UnsupportedEncodingException {
        String resultStr = "";
        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(temp);
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                        '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];
                resultStr += new String(ob);
            }
            return resultStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法名称：getEncryptedPwd
     * 功    能：获取加密密码
     * 参    数：sn,password(商户号，商户密码)
     * 返 回 值：加密密码
     */
    private static String getEncryptedPwd(String sn, String password) {
        String pwd = "";
        try {
            pwd = getMD5(sn + password).toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("pwd===" + pwd);
        return pwd;
    }

    /**
     * 方法名称：mdsmssend
     * 功    能：发送短信
     * 参    数：mobile,content,ext,stime,rrid,msgfmt(手机号，内容，扩展码，定时时间，唯一标识，内容编码)
     * 返 回 值：唯一标识，如果不填写rrid将返回系统生成的
     */
    public static String mdsmssend(String mobile, String content, String ext, String stime,
                                    String rrid, String msgfmt) {
        String result = "";
        String soapAction = "http://entinfo.cn/mdsmssend";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<mdsmssend  xmlns=\"http://entinfo.cn/\">";
        xml += "<sn>" + sn + "</sn>";
        xml += "<pwd>" + getEncryptedPwd(sn, pwd) + "</pwd>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<ext>" + ext + "</ext>";
        xml += "<stime>" + stime + "</stime>";
        xml += "<rrid>" + rrid + "</rrid>";
        xml += "<msgfmt>" + msgfmt + "</msgfmt>";
        xml += "</mdsmssend>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        System.out.println("短信参数====" + xml);
        URL url;
        try {
            url = new URL(thirdUrl);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
                Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            System.out.println("发送短信接口返回结果:result====" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
