package com.jebao.p2p.web.api.conf;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2016/10/11.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置拦截器
     *
     * @param registry
     * @author yzd
     */
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthorizeInterceptor())
//                .addPathPatterns("/api/**")
//                .excludePathPatterns("/api/tempTest/*")
//                .excludePathPatterns("/api/account/doLogin");
    }
    /**
     * SpringMvc_@RequestMapping设置Router Url大小写不敏感
     * http://www.cnblogs.com/gossip/p/5441358.html
     * 如何取消 /index.*映射
     * http://www.oschina.net/question/190714_116949
     * */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
        configurer.setUseSuffixPatternMatch(false);
    }
    @Bean
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory()
    {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        //tomcatFactory.setPort(8081);
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
        return tomcatFactory;
    }
}
