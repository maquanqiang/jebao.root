package com.jebao.p2p.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2016/10/19.
 */
@SpringBootApplication
@ComponentScan("com.jebao.p2p.web")
public class ApplicationP2PWeb {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationP2PWeb.class, args);
    }
    

}
