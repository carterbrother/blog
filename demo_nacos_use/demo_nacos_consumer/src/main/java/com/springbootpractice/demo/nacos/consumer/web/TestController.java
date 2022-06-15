package com.springbootpractice.demo.nacos.consumer.web;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author carter
 * create_date  2020/5/27 11:28
 * description     测试服务调用
 */
@RestController
public class TestController {

    private final LoadBalancerClient loadBalancerClient;

    public TestController(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @GetMapping("/callHello")
    public String callHello(){

        ServiceInstance nacosProvider = loadBalancerClient.choose("demo_nacos_provider");

        String url = nacosProvider.getUri() + "/hello?name=lifuchun";

        RestTemplate restTemplate = new RestTemplate();

        String res = restTemplate.getForObject(url, String.class);

        return "invoke : "+ url + " return: "+ res;

    }

}
