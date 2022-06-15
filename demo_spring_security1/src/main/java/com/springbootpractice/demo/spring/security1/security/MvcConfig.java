package com.springbootpractice.demo.spring.security1.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 说明：mvc配置
 * @author carter
 * 创建时间： 2020年02月09日 12:25 上午
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login/page").setViewName("login");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }
}
