package com.springbootpractice.demo.data.dict.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Json工具类
 * @date 2019年05月22日 2:58 PM
 * @Copyright (c) carterbrother
 */
public class JsonUtil {

    private JsonUtil() {
    }

    private static JsonMapper jsonMapper = JsonMapper.getDefault();

    public static String toJson(Object object) {
        return object == null ? "{}" : jsonMapper.toJson(object);
    }

    public static String toJsonList(Object object) {
        return object == null ? "[]" : jsonMapper.toJson(object);
    }

    public static String toJsonIgnoreNullField(Object object) {
        return JsonMapper.nonNullMapper().toJson(object);
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) {
        return jsonMapper.toObject(jsonString, clazz);
    }

    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        return jsonMapper.toList(jsonString, clazz);
    }


    private static class JsonMapper {
        private static JsonMapper defaultMapper;
        private static ObjectMapper mapper;

        public JsonMapper() {
            this(null);
        }

        public JsonMapper(JsonInclude.Include include) {
            mapper = new ObjectMapper();
            if (include != null) {
                mapper.setSerializationInclusion(include);
            }

            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, false);
            String timeZone = StringUtils.isBlank(timeZone = System.getProperty("user.timezone", "Asia/Shanghai")) ? "Asia/Shanghai" : timeZone;
            mapper.setTimeZone(TimeZone.getTimeZone(timeZone));
        }

        public JsonMapper enumAndStringConvert(boolean enabled) {
            return dateAndTimestampConvert(enabled);
        }

        public JsonMapper dateAndTimestampConvert(boolean enabled) {
            if (enabled) {
                mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
                mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
            } else {
                mapper.disable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
                mapper.disable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
            }

            return this;
        }

        public JsonMapper timeZone(int timeZone) {
            mapper.setTimeZone(TimeZone.getTimeZone("GMT+" + timeZone));
            return this;
        }

        public static JsonMapper nonEmptyMapper() {
            return new JsonMapper(JsonInclude.Include.NON_EMPTY);
        }

        public static JsonMapper nonNullMapper() {
            return new JsonMapper(JsonInclude.Include.NON_NULL);
        }

        public static JsonMapper nonDefaultMapper() {
            return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
        }

        public String toJson(Object object) {
            try {
                return mapper.writeValueAsString(object);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }

        public <T> T toObject(String jsonString, Class<T> clazz) {
            if (StringUtils.isEmpty(jsonString)) {
                return null;
            } else {
                return readT(jsonString, clazz);
            }
        }

        private <T> T readT(String jsonString, Class<T> clazz) {
            try {
                return mapper.readValue(jsonString, clazz);
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }

        public <T> List<T> toList(String jsonString, Class<T> elementType) {
            if (StringUtils.isEmpty(jsonString)) {
                return null;
            } else {
                JavaType javaType = mapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class, new Class[]{elementType});
                return (List) this.toObject(jsonString, javaType);
            }
        }

        public <T> T toObject(String jsonString, JavaType javaType) {
            if (StringUtils.isEmpty(jsonString)) {
                return null;
            } else {
                try {
                    return mapper.readValue(jsonString, javaType);
                } catch (IOException var4) {
                    throw new RuntimeException(var4);
                }
            }
        }

        public <T> T update(String jsonString, T object) {
            try {
                return mapper.readerForUpdating(object).readValue(jsonString);
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

        public String toJsonP(String functionName, Object object) {
            return this.toJson(new JSONPObject(functionName, object));
        }

        public static ObjectMapper getMapper() {
            return mapper;
        }

        public static JsonMapper getDefault() {
            if (defaultMapper == null) {
                defaultMapper = new JsonMapper();
                defaultMapper.dateAndTimestampConvert(false);
            }

            return defaultMapper;
        }


    }

}
