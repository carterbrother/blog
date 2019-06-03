package com.springx.bootdubbo.starter.rest.core;

import com.springx.bootdubbo.common.bean.RestContextBean;
import com.springx.bootdubbo.common.bean.RestResponseBean;
import com.springx.bootdubbo.common.enums.ErrorCodeMsgEnum;
import com.springx.bootdubbo.common.exception.BaseException;
import com.springx.bootdubbo.common.util.JsonUtil;
import com.springx.bootdubbo.common.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * 作者:     carter
 * 创建日期:  2019/6/1 上午10:23
 * 描述:     统一返回格式，正常情况或者出现异常的时候
 */
@RestControllerAdvice
@Slf4j
@Component
public class ResponseBodyAndExceptionHandleBean implements ResponseBodyAdvice {

    public ResponseBodyAndExceptionHandleBean(){
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponseBean handleException(HttpServletRequest request, Exception e) {
        Integer code = ErrorCodeMsgEnum.ERROR.code();
        String msg = null;
        String exceptionMsg = "";
        Object data = null;
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            code = baseException.getCode();
            msg = baseException.getMsg();
            exceptionMsg = msg;
        } else if (e instanceof NoHandlerFoundException) {
            code = ErrorCodeMsgEnum.NOT_FOUND.code();
            msg = ErrorCodeMsgEnum.NOT_FOUND.getMsg();
        } else {
            exceptionMsg = e.getLocalizedMessage();
            msg = exceptionMsg;
            if (SystemUtil.isOnlineEnv()) {
                msg = "系统错误";
            }
            log.error("\n===param_exception===\n===requestId==={}\n===exception==={}", RestContextBean.getInstance().getRequestId(), e);
        }
        return RestResponseBean.builder().code(code).msg(msg).build();
    }



    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        if (Objects.isNull(returnType)){
            return  true;
        }

        return returnType.hasMethodAnnotation(ResponseBody.class)
                || returnType.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class);

    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RestResponseBean restResponse= getResponse(body);
        log.info("\n===param_response===\n===requestId==={}\n===json==={}", RestContextBean.getInstance().getRequestId(), JsonUtil.toJson(restResponse));
        return restResponse;
    }

    private RestResponseBean getResponse(Object body) {
        if (Objects.isNull(body)){
            return RestResponseBean.builder().code(ErrorCodeMsgEnum.SUCCESS.code())
                    .msg(ErrorCodeMsgEnum.SUCCESS.getMsg())
                    .build();
        }

        if (body instanceof RestResponseBean){
            RestResponseBean restResponseBean = (RestResponseBean) body;
            String msg = I18nUtils.getMessage(restResponseBean.getCode() + "", restResponseBean.getMsg());
            return RestResponseBean.builder().code(restResponseBean.getCode())
                    .msg(msg)
                    .data(restResponseBean.getData())
                    .build();
        }

        if (body instanceof LinkedHashMap) {
            LinkedHashMap hashMap = (LinkedHashMap) body;
            if (hashMap.containsKey("error") && hashMap.containsKey("path") && hashMap.containsKey("status") && hashMap.containsKey("timestamp")) {
                Object status = hashMap.get("status");
                Integer code = ErrorCodeMsgEnum.SUCCESS.code();
                if (StringUtils.isNumeric(Objects.toString(status))) {
                    code = Integer.parseInt(Objects.toString(status));
                }
                return RestResponseBean.builder().code(code)
                        .msg(String.valueOf(hashMap.get("error")))
                        .build();
            }
        }

        return RestResponseBean.builder().code(ErrorCodeMsgEnum.SUCCESS.code())
                .msg(ErrorCodeMsgEnum.SUCCESS.getMsg())
                .data(body)
                .build();
    }
}
