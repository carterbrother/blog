package com.zengame.platweb.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {

        try {
            new Server().start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SpringApplication.run(Demo2Application.class, args);
    }

}
