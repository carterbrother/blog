package com.carterbrother.springbootpractice.chapter5_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class Chapter5DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(Chapter5DemoApplication.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Chapter5DemoApplication.class, args);

        final DataSource bean = applicationContext.getBean(DataSource.class);

        logger.info("bean class  DruidDatasource ? : {} " ,bean.getClass().getName());

    }

}
