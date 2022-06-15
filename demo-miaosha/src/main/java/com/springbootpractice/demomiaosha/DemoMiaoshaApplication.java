package com.springbootpractice.demomiaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.springbootpractice.demomiaosha.dao",
        annotationClass = Repository.class

)
public class DemoMiaoshaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMiaoshaApplication.class, args);
    }

}
