package com.springbootpractice.demo.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(annotationClass = Repository.class,
        basePackages = "com.springbootpractice.demo.redis.dao.mapper")
@EnableCaching
public class DemoRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRedisApplication.class, args);
    }

}
