package com.example.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.util.TimeZone;

@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

    @Override
    public ObjectMapper getContext(Class<?> type) {
        ObjectMapper mapper = new ObjectMapper();
        //设置输出时包含属性的风格，只有值非空才输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //设置允许字段名不唯一
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
        //默认时区
        String timeZone = System.getProperty("user.timezone", "Asia/Shanghai");
        mapper.setTimeZone(TimeZone.getTimeZone(timeZone));
        //枚举转换为toString输出
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        return mapper;
    }
}