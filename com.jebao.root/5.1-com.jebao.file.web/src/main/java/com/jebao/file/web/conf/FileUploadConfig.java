package com.jebao.file.web.conf;

import com.sun.jna.Platform;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by Administrator on 2016/12/15.
 */
@Configuration
public class FileUploadConfig {
    //限制上传文件的大小
    //默认情况下是1MB的大小
    //上传文件的小最好设置为2M
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20MB");
        //factory.setMaxRequestSize("128KB");
        //factory.setMaxFileSize(1024L * 1024L* 1024L);
        if(Platform.isLinux())
            factory.setLocation("/var/tmp");
        return factory.createMultipartConfig();
    }

}