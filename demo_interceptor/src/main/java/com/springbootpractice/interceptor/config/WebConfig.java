package com.springbootpractice.interceptor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootpractice.interceptor.config.interceptor.MyInterceptor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：配置拦截器和设置统一返回格式
 * @author carter
 * 创建时间： 2020年02月19日 11:03 下午
 **/
@Configuration
@ControllerAdvice
public class WebConfig implements WebMvcConfigurer, ResponseBodyAdvice {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor myIntercepter = new MyInterceptor();
        registry.addInterceptor(myIntercepter).addPathPatterns("/**");
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Map<String, Object> map = new HashMap();
        map.put("result", "true");
        map.put("data", body);
        return new ObjectMapper().writeValueAsString(map);
    }
}
