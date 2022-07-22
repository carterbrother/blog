package com.spring.story.demosleuth.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.compiler.PluginProtos;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 处理响应信息
 *
 * @author Carter.li
 * @createtime 2022/7/22 18:27
 */
@Component
@Slf4j
@ControllerAdvice(annotations = {Controller.class, RestController.class})
public class ResConfig implements ResponseBodyAdvice {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        R<Object> r;
        String traceId = MDC.get("traceId");

        if (body instanceof R) {

            r = (R<Object>) body;
            r.setTraceId(traceId);

        } else {

            r = R.ofData(body, traceId);

        }

        if (selectedContentType.includes(MediaType.APPLICATION_JSON)){
            return r;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            log.error("json convert error ",e);
            throw new RuntimeException(e);
        }
    }
}
