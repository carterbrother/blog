package com.springbootpractice.egproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.springbootpractice.api.user")
@EnableCircuitBreaker
public class EgProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgProductApplication.class, args);
    }

}
