package com.springbootpractice.hystrixdashbard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashbardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashbardApplication.class, args);
    }

}
