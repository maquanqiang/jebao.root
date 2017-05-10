package com.jebao.file.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Administrator on 2016/12/15.
 */
@SpringBootApplication
@ComponentScan("com.jebao.file.web")
public class ApplicationFileWeb {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationFileWeb.class, args);
    }
}