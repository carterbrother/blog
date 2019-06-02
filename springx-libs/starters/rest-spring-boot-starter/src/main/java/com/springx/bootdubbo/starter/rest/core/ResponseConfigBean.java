package com.springx.bootdubbo.starter.rest.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springx.bootdubbo.common.bean.RestContextBean;
import com.springx.bootdubbo.common.bean.RestResponseBean;
import com.springx.bootdubbo.common.enums.ErrorCodeMsgEnum;
import com.springx.bootdubbo.starter.rest.config.RestPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description web配置
 * @date 2019年05月17日 5:19 PM
 * @Copyright (c) carterbrother
 */
@Slf4j
public class ResponseConfigBean implements InitializingBean {
    /**
     * 1.扫描路径 == 可不做，使用springboot默认的扫描路径即可
     * 2.cros过滤
     * 3.json设置空值不返回 ==finish
     * 4.全局异常处理 ==finish
     * 5.全局过滤器，使用拦截器处理了 设置context参数，mdc, 设置跨域支持 ==finish
     * 6.健康检查封装
     * 7.文件上传 == 可不做，直接使用7N或者阿里云的云存储解耦
     */

    private ApplicationContext applicationContext;
    private RestPropertiesConfig restPropertiesConfig;

    public ResponseConfigBean(ApplicationContext applicationContext, RestPropertiesConfig restPropertiesConfig) {
        this.applicationContext = applicationContext;
        this.restPropertiesConfig = restPropertiesConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("===>install response wrapper ");
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
    }

}
