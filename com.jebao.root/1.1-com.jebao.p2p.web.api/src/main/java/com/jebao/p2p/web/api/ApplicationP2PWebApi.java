package com.jebao.p2p.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2016/10/19.
 */
@SpringBootApplication
@ComponentScan("com.jebao.p2p.web.api,com.jebao.p2p.service.inf,com.jebao.p2p.service.impl,com.jebao.jebaodb.dao,com.jebao.thirdPay.fuiou")
public class ApplicationP2PWebApi {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationP2PWebApi.class, args);
    }
}
