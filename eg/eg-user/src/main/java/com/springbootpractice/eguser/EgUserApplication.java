package com.springbootpractice.eguser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EgUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgUserApplication.class, args);
    }

}
