package com.jebao.erp.web.controller;

import com.jebao.erp.web.conf.EmbeddedServletInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Administrator on 2016/10/21.
 */
@Controller
@RequestMapping("/tempTest/")
public class _TempTestController {
    //
    @RequestMapping("doWork")
    @ResponseBody
    public String doWork() {
        return "doWork";
    }
    String serviceName="com.jebao.erp.web";//服务名称
    String versionNum="20161208153801";//服务版本号
    @RequestMapping("version")
    @ResponseBody
    public String version() throws SocketException {
        String host = getIpInLinux();
        int port= EmbeddedServletInstance.getServerPort();
        String timestamp= currentTime();
        String info=String.format("name:%s|version:%s|host:%s|port:%d|timestamp:%s",serviceName,versionNum,host,port,timestamp);
        return info;
    }
    private  String currentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }
    //region getIp
    private  String getIpInLinux() {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                //System.out.println(netInterface.getName());
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        if (ip.equals("127.0.0.1")) continue;
                        //System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            return "get server host Exception e(linux):" + e.getMessage();
        }
        return null;
    }
    //endregion
}
