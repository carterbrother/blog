package com.springbootpractice.egzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class EgZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgZuulApplication.class, args);
    }

}
