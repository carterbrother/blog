package com.carterbrother.springbootpractice.chapter1_0xmlmvcdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description springMvc容器配置,只扫描Controller
 * @date 2019年02月13日 3:41 PM
 * @Copyright (c) carterbrother
 */
@Configuration
@ComponentScan(basePackages = {"com.carterbrother.springbootpractice.chapter1_0xmlmvcdemo.web"})
@EnableWebMvc
public class SpringMvcContainer extends WebMvcConfigurationSupport {


    /**
     * 配置json的解析器
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(mappingJackson2HttpMessageConverter);
    }

}
