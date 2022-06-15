package com.carterbrother.springbootpractice.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年04月10日 4:33 PM
 * @Copyright (c) carterbrother
 */
@Configuration
public class WebMvcBean extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

     registry.addViewController("/login").setViewName("/login.html");

    }
}
