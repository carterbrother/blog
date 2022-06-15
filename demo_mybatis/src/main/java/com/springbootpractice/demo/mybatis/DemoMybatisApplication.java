package com.springbootpractice.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.springbootpractice.demo.mybatis.dao",
        annotationClass = Repository.class
)
public class DemoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisApplication.class, args);
    }

}
