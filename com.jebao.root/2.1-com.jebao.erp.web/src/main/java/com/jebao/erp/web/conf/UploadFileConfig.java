package com.jebao.erp.web.conf;

import com.sun.jna.Platform;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by Lee on 2017/3/7.
 */
@Configuration
public class UploadFileConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        if(Platform.isLinux())
            factory.setLocation("/var/tmp");
        return factory.createMultipartConfig();
    }
}
