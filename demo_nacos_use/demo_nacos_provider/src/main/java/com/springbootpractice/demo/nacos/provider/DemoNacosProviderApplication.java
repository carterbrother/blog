package com.springbootpractice.demo.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNacosProviderApplication.class, args);
    }

}
