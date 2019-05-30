package com.springx.bootdubbo.starter.rest.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springx.bootdubbo.common.bean.RestContextBean;
import com.springx.bootdubbo.common.bean.RestResponseBean;
import com.springx.bootdubbo.common.enums.ErrorCodeMsgEnum;
import com.springx.bootdubbo.common.exception.BaseException;
import com.springx.bootdubbo.common.util.SystemUtil;
import com.springx.bootdubbo.starter.rest.core.RestContextInterceptor;
import com.springx.bootdubbo.starter.rest.core.WebMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 自动配置Rest相关，配置拦截器，正常返回预定格式的json数据；
 * 异常也返回预定格式的json数据
 * @date 2019年05月22日 1:56 PM
 * @Copyright (c) carterbrother
 */
@Configuration
@EnableConfigurationProperties(RestPropertiesConfig.class)
@Slf4j
@ControllerAdvice
public class RestAutoConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Resource
    private RestPropertiesConfig restPropertiesConfig;

    @Bean
    @ConditionalOnProperty(prefix = "rest.config", value = "enabled", havingValue = "true")
    public WebMvcConfig webMvcConfig() {
        Assert.notNull(restPropertiesConfig, "rest配置不能为空");
        log.info("===>install interceptor ");

        InterceptorRegistry interceptorRegistry = applicationContext.getBean(InterceptorRegistry.class);
        interceptorRegistry.addInterceptor(new RestContextInterceptor(applicationContext,restPropertiesConfig)).addPathPatterns("/*");

        log.info("===>install json converter");
        MappingJackson2HttpMessageConverter messageConverter = applicationContext.getBean(MappingJackson2HttpMessageConverter.class);
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);


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

        return null;
    }

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
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
