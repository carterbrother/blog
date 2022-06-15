package com.springbootpractice.springboot.demo.ribbon.consumer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年02月24日 5:45 下午
 **/
@RestController
public class IndexController {


    private final RestTemplate restTemplate;

    public IndexController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(path="callHello")
    public String callHello(){

        String helloReturn = restTemplate.getForEntity("http://DEMO_EUREKA_CLIENT_APP_A/app_a/hello",String.class).getBody();
        return "call hello return: " + helloReturn;
    }
}
