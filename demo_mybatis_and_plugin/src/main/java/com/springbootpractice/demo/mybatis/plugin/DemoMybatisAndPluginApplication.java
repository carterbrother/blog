package com.springbootpractice.demo.mybatis.plugin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(annotationClass = Repository.class,
        basePackages = "com.springbootpractice.demo.mybatis.plugin.dao.mapper")
public class DemoMybatisAndPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisAndPluginApplication.class, args);
    }

}
