package com.springbootpractice.demo_intercepter;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class Application {

    public static void main(String[] args) {
        //配置
        System.setProperty("spring.mvc.throw-exception-if-no-handler-found","true");
        System.setProperty("spring.resources.add-mappings","false");

        SpringApplication.run(Application.class, args);
    }



}
