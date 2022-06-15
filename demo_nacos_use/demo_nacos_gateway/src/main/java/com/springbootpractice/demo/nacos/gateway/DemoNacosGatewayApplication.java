package com.springbootpractice.demo.nacos.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoNacosGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNacosGatewayApplication.class, args);
    }

}