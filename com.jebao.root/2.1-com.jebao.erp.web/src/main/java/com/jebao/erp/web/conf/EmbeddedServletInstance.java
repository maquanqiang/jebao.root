package com.jebao.erp.web.conf;

/**
 * Created by Administrator on 2016/12/8.
 */
public class EmbeddedServletInstance {
    private static int serverPort;

    public static int getServerPort() {
        return serverPort;
    }

    public static void setServerPort(int serverPort) {
        EmbeddedServletInstance.serverPort = serverPort;
    }
}
