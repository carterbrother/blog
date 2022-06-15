package com.springbootpractice.demo.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableReactiveMongoRepositories(basePackages = "com.springbootpractice.demo.webflux.dao.repository")
public class DemoWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebfluxApplication.class, args);
    }

}
