package com.springbootpractice.egzuul;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 通过外置的容器运行springboot
 * @date 2019年06月21日 15:32
 * @Copyright (c) carterbrother
 */
public class ServletInitializer extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EgZuulApplication.class);
    }
}
