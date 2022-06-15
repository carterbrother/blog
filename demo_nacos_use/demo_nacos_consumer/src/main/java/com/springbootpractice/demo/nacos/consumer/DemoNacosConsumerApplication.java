package com.springbootpractice.demo.nacos.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoNacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNacosConsumerApplication.class, args);
    }

}
