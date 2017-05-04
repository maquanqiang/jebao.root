package com.jebao.p2p.web.conf;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

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
        registry.addInterceptor(new AuthorizeInterceptor())
                .addPathPatterns("/user*/**")
                .addPathPatterns("/account/logout");
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
    //解决下面的问题
    //ERROR org.apache.velocity : ResourceManager : unable to find resource 'xxx.html.html' in any resource loader
    //ERROR org.apache.velocity : ResourceManager : unable to find resource 'xxx.html.vm' in any resource loader
    @Bean
    public ViewResolver viewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setViewClass(VelocityToolboxView.class);
        resolver.setPrefix("");
        resolver.setSuffix(".html");
        return resolver;
    }
    //解决静态资源不能缓存的问题
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Resources controlled by Spring Security, which
        // adds "Cache-Control: must-revalidate".
        registry.addResourceHandler("/content/**")
                .addResourceLocations("classpath:/static/content/")
                .setCachePeriod(3600*24*100);//设置静态资源缓存时间为100天
    }
}
