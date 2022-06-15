package com.lifesense.sz.research_formimageread.configfile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description AIP的连接配置对象
 * @date 2019年02月18日 12:38 PM
 * @Copyright (c) carterbrother
 */
@Component
@ConfigurationProperties("aip")
public class AIPConfig {

    private String appName;
    private String appId;
    private String apiKey;
    private String secretKey;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
