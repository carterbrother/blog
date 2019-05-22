package com.springx.bootdubbo.starter.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 暴露出来的配置
 * @date 2019年05月22日 1:57 PM
 * @Copyright (c) carterbrother
 */
@ConfigurationProperties(prefix = "rest.config")
public class RestPropertiesConfig {
    /**
     * 登录校验的URL,url校验规范如下；
     * Integer  AppTypeEnum 产品应用ID
     * String   loginId 登录ID
     * String   accessToken 令牌
     */
    private String loginCheckUrl;
    /**
     * 登录校验的API,需要实现ILoginCheck接口
     */
    private String loginCheckApi;


}
