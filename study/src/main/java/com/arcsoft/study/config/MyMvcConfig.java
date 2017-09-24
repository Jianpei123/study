package com.arcsoft.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    /*
     * @Override public void addCorsMappings(CorsRegistry registry) { // TODO
     * 自动生成的方法存根 registry.addMapping("/**") .allowedOrigins("*")
     * .allowCredentials(true)//就是这个啦 .allowedMethods("GET", "POST", "DELETE",
     * "PUT") .maxAge(3600); }
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO 自动生成的方法存根
        registry.addResourceHandler("/course/**")
                .addResourceLocations("file:C:\\Users\\10313\\Desktop\\onlinelearning\\course\\");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO 自动生成的方法存根
        registry.addInterceptor(new SessionTimeoutInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
