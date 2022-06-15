package com.carterbrother.springbootpractice.chapter3_demo;

import com.carterbrother.springbootpractice.chapter3_demo.configfile.FuckObject;
import com.carterbrother.springbootpractice.chapter3_demo.configfile.FuckProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:fuck.properties"})
public class Chapter3DemoApplication {


    private static final Logger logger = LoggerFactory.getLogger(Chapter3DemoApplication.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Chapter3DemoApplication.class, args);

        final FuckProperties fu = applicationContext.getBean(FuckProperties.class);
        final FuckObject fuckObject = applicationContext.getBean(FuckObject.class);

        logger.info("username: {}" , fu.getUsername());

        logger.info("fuckObject.username: {}" , fuckObject.getUsername());

    }

}

