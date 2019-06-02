package com.springx.bootdubbo.starter.rest.config;

import com.springx.bootdubbo.starter.rest.core.ResponseBodyAndExceptionHandleBean;
import com.springx.bootdubbo.starter.rest.core.RestContextInterceptorBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

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
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class RestAutoConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Resource
    private RestPropertiesConfig restPropertiesConfig;

    @Bean
    @ConditionalOnProperty(prefix = "rest.config", value = "enabled", havingValue = "true")
    public ResponseBodyAndExceptionHandleBean exceptionHandlerBean(){
        log.info("===>install exception handler AND response wrapper ");
        applicationContext.getBean(DispatcherServlet.class).setThrowExceptionIfNoHandlerFound(true);
        return new ResponseBodyAndExceptionHandleBean();
    }


    @Bean
    @ConditionalOnProperty(prefix = "rest.config", value = "enabled", havingValue = "true")
    public WebMvcConfigurer webMvcConfigurer(){
        log.info("===>install interceptor");
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new RestContextInterceptorBean(restPropertiesConfig));
            }
        };
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
