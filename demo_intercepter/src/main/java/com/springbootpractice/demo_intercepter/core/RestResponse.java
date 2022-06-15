package com.springbootpractice.demo_intercepter.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Rest统一返回给前端的数据对象
 * @date 2019年05月17日 5:31 PM
 * @Copyright (c) carterbrother
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     *返回消息
     */
    private String  msg;
    /**
     * 响应数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @Override
    public String toString() {
        return new StringJoiner(", ", RestResponse.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .add("data=" + data)
                .toString();
    }

    static class CodeDict {
        public static final Integer SUCCESS_CODE =200;
        public static final Integer ERROR_CODE = 500;
        public static final Integer NOT_FOUND_CODE = 404;
    }

    static class MsgDict{
        public static final String SUCCESS_MSG ="success";
        public static final String ERROR_MSG ="fail";
        public static final String NOT_FOUND_MSG = "not found";
    }

}
