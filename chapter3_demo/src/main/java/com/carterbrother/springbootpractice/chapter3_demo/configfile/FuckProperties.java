package com.carterbrother.springbootpractice.chapter3_demo.configfile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 4:22 PM
 * @Copyright (c) carterbrother
 */
@Component
public class FuckProperties {
    @Value("${fuck.username}")
    private String username;
    @Value("${fuck.password}")
    private String password;
    private String url;
    private String driver;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    @Value("${fuck.url}")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    @Value("${fuck.driver}")
    public void setDriver(String driver) {
        System.out.println("driver: "+driver);
        this.driver = driver;
    }
}
