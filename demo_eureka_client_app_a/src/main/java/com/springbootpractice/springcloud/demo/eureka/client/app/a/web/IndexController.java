package com.springbootpractice.springcloud.demo.eureka.client.app.a.web;

import com.netflix.appinfo.EurekaInstanceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年02月24日 5:00 下午
 **/
@RestController
@RequestMapping(path = "/app_a")
@Slf4j
public class IndexController {


    private final EurekaInstanceConfig eurekaInstanceConfig;

    public IndexController(EurekaInstanceConfig eurekaInstanceConfig) {
        this.eurekaInstanceConfig = eurekaInstanceConfig;
    }


    @GetMapping(path = "/hello")
    public String hello(HttpServletRequest request) {
        log.info("path:{},servieceInstance.host={}, serviceInstance.id={}",
                request.getRequestURI(),
                eurekaInstanceConfig.getHostName(true),
                eurekaInstanceConfig.getInstanceId()
        );
        return "hello world";
    }

}
