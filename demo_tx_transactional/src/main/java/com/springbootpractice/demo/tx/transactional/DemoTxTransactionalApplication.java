package com.springbootpractice.demo.tx.transactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.springbootpractice.demo.tx.transactional.dao.mapper",
        annotationClass = Repository.class)
public class DemoTxTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTxTransactionalApplication.class, args);
    }

}
