package com.springbootpractice.lesson.lesson3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 配置xml输出和输出
 * @date 2019年05月15日 1:51 PM
 * @Copyright (c) carterbrother
 */
@Configuration
public class MessageFormateConfig extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters = new LinkedList<>();
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
        builder.indentOutput(true);

        converters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
        super.configureMessageConverters(converters);
    }



}
