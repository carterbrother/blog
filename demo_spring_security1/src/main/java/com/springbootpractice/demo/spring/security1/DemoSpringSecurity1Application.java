package com.springbootpractice.demo.spring.security1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(annotationClass = Repository.class,
        basePackages = "com.springbootpractice.demo.spring.security1.dao.mapper")
public class DemoSpringSecurity1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringSecurity1Application.class, args);
    }

}
