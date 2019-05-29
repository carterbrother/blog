package com.springx.bootdubbo.starter.rest.config;

import com.springx.bootdubbo.common.bean.RestContextBean;
import com.springx.bootdubbo.common.bean.RestResponseBean;
import com.springx.bootdubbo.common.enums.ErrorCodeMsgEnum;
import com.springx.bootdubbo.common.exception.BaseException;
import com.springx.bootdubbo.common.util.SystemUtil;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rest.config", value = "enabled", havingValue = "true")
    public WebMvcConfig webMvcConfig() {
        Assert.notNull(restPropertiesConfig, "rest配置不能为空");
        return new WebMvcConfig(applicationContext,restPropertiesConfig);
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
