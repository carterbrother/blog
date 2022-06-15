package com.springbootpractice.demo.demo_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.springbootpractice.demo.demo_jpa.dao.repository")
@EntityScan(basePackages = "com.springbootpractice.demo.demo_jpa.dao.entity")
public class DemoJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }

}
