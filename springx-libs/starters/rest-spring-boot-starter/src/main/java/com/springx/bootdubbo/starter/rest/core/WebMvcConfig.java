package com.springx.bootdubbo.starter.rest.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springx.bootdubbo.common.bean.RestContextBean;
import com.springx.bootdubbo.common.bean.RestResponseBean;
import com.springx.bootdubbo.common.enums.ErrorCodeMsgEnum;
import com.springx.bootdubbo.common.exception.BaseException;
import com.springx.bootdubbo.common.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description web配置
 * @date 2019年05月17日 5:19 PM
 * @Copyright (c) carterbrother
 */
@Slf4j
@Configuration
@ControllerAdvice
public class WebMvcConfig implements WebMvcConfigurer, InitializingBean {
    /**
     * 1.扫描路径 == 可不做，使用springboot默认的扫描路径即可
     * 2.cors过滤
     * 3.json设置空值不返回 ==finish
     * 4.全局异常处理 ==finish
     * 5.全局过滤器，使用拦截器处理了 设置context参数，mdc, 设置跨域支持 ==finish
     * 6.健康检查封装
     * 7.文件上传 == 可不做，直接使用7N或者阿里云的云存储解耦
     */

    @Autowired
    private RestContextInterceptor myInterceptor;
    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponseBean handleException(HttpServletRequest request, Exception e) {
        Integer code = ErrorCodeMsgEnum.ERROR.code();
        String msg = null;
        String exceptionMsg = "";
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            code = baseException.getCode();
            msg = baseException.getMsg();
            exceptionMsg = msg;
        } else if (e instanceof NoHandlerFoundException) {
            code = ErrorCodeMsgEnum.NOT_FOUND.code();
            msg = ErrorCodeMsgEnum.NOT_FOUND.getMsg();
        } else {
            exceptionMsg = StringUtils.abbreviate(e.getLocalizedMessage(), 500);
            msg = exceptionMsg;
            if (SystemUtil.isOnlineEnv()) {
                msg = "系统错误";
            }
        }
        final RestContextBean restContext = RestContextBean.getInstance();
        log.error("\n===param_exception===\n===requestId==={}===\n===Url==={},\n===Msg==={}\n===useTime==={}\n===error===",
                restContext.getRequestId(),
                request.getContextPath().concat(request.getRequestURL().toString()),
                exceptionMsg,
                Optional.ofNullable(restContext.getStopWatch()).isPresent() ? restContext.getStopWatch().getSplitTime() : 0,
                e);
        return RestResponseBean.builder().code(code).msg(msg).build();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("===>注册拦截器");
        //增加拦截器
        registry.addInterceptor(myInterceptor).addPathPatterns("/*");
    }

    @Override
    public void afterPropertiesSet() throws Exception {


        //配置json的格式
        messageConverter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, Boolean.FALSE)
                .setTimeZone(TimeZone.getTimeZone(System.getProperty("user.timezone", "Asia/Shanghai")))
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);


        List<HandlerMethodReturnValueHandler> handlers2 = requestMappingHandlerAdapter.getReturnValueHandlers().stream().map(item -> {
            if (item instanceof RequestResponseBodyMethodProcessor) {
                return new HandlerMethodReturnValueHandler() {
                    @Override
                    public boolean supportsReturnType(MethodParameter returnType) {
                        return returnType.hasMethodAnnotation(ResponseBody.class)
                                || returnType.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class);

                    }

                    @Override
                    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
                        mavContainer.setRequestHandled(Boolean.TRUE);
                        final RestResponseBean restResponse = RestResponseBean.builder().code(ErrorCodeMsgEnum.SUCCESS.code())
                                .msg(ErrorCodeMsgEnum.SUCCESS.getMsg())
                                .data(returnValue)
                                .build();
                        messageConverter.write(restResponse, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(webRequest.getNativeResponse(HttpServletResponse.class)));

                        log.info("\n===param_response===\n===requestId==={}\n===json==={}", RestContextBean.getInstance().getRequestId(), messageConverter.getObjectMapper().writeValueAsString(restResponse));

                    }
                };
            }

            return item;
        }).collect(Collectors.toList());


        requestMappingHandlerAdapter.setReturnValueHandlers(handlers2);
    }
}
