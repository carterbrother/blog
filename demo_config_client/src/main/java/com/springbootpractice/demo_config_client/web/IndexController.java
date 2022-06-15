package com.springbootpractice.demo_config_client.web;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 说明：测试访问config server 中配置的属性
 * @author carter
 * 创建时间： 2019年09月14日 11:16
 **/
@RefreshScope
@RestController
public class IndexController implements EnvironmentAware {

    private  Environment environment;

    public IndexController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(path = "/getConfig/{key}")
    public Object getKey(@PathVariable("key") String key){
        return environment.getProperty(Objects.requireNonNull(key,"key不能为空"),String.format("not found by key %s",key));
    }

    @Override
    public void setEnvironment(org.springframework.core.env.Environment environment) {
        this.environment = environment;
    }
}
