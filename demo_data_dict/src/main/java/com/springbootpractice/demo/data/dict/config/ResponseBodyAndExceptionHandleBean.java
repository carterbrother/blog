package com.springbootpractice.demo.data.dict.config;

import com.springbootpractice.demo.data.dict.param.core.RestResponseBean;
import com.springbootpractice.demo.data.dict.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: carter
 * 创建日期:  2019/6/1 上午10:23
 * 描述:     统一返回格式，正常情况或者出现异常的时候
 */
@RestControllerAdvice
@Slf4j
public class ResponseBodyAndExceptionHandleBean implements ResponseBodyAdvice {

   private static List<Class> clzList =  Arrays.asList(springfox.documentation.swagger2.web.Swagger2Controller.class,springfox.documentation.swagger.web.ApiResourceController.class);
    public ResponseBodyAndExceptionHandleBean() {
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponseBean handleException(Exception e) {
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String msg = null;
        if (e instanceof NoHandlerFoundException) {
            code = HttpStatus.NOT_FOUND.value();
            msg = HttpStatus.NOT_FOUND.getReasonPhrase();
        } else {
            msg = e.getLocalizedMessage();
            log.error("\n===param_exception===\n===exception==={}", msg, e);
        }
        return RestResponseBean.builder().code(code).msg(msg).build();
    }


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(ResponseBody.class)
                || Objects.requireNonNull(returnType.getMethod()).getDeclaringClass().isAnnotationPresent(RestController.class)
                || clzList.stream().anyMatch(clz->returnType.getMethod().getDeclaringClass().equals(clz));

    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (clzList.stream().anyMatch(clz->returnType.getMethod().getDeclaringClass().equals(clz))) {
            return body;
        }

        RestResponseBean restResponse = getResponse(body);
        log.info("\n===param_response===\n===json==={}", JsonUtil.toJson(restResponse));
        return restResponse;
    }

    private RestResponseBean getResponse(Object body) {
        if (Objects.isNull(body)) {
            return RestResponseBean.SUCCESS_BEAN;
        }

        if (body instanceof RestResponseBean) {
            return (RestResponseBean) body;
        }

        if (body instanceof LinkedHashMap) {
            LinkedHashMap hashMap = (LinkedHashMap) body;
            if (hashMap.containsKey("error") && hashMap.containsKey("path") && hashMap.containsKey("status") && hashMap.containsKey("timestamp")) {
                Object status = hashMap.get("status");
                Integer code = HttpStatus.OK.value();
                if (StringUtils.isNumeric(Objects.toString(status))) {
                    code = Integer.parseInt(Objects.toString(status));
                }
                return RestResponseBean.builder().code(code)
                        .msg(String.valueOf(hashMap.get("error")))
                        .build();
            }
        }

        return RestResponseBean.builder().code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .data(body)
                .build();
    }
}
