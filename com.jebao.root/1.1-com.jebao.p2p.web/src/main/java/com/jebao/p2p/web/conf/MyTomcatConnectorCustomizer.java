package com.jebao.p2p.web.conf;

/**
 * Created by Administrator on 2016/10/31.
 */

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * Created by Administrator on 2016/10/31.
 * 参考：
 * SpringBoot优化内嵌的Tomcat
 * http://www.cnblogs.com/softidea/p/5751596.html
 *
 */
public class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
    @Override
    public void customize(Connector connector) {

        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        //设置最大连接数
        protocol.setMaxConnections(2000);
        //设置最大线程数
        protocol.setMaxThreads(2000);
        protocol.setConnectionTimeout(30000);
    }
}
