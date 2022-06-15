package com.springbootpractice.democonfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DemoConfigServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(DemoConfigServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
//        SpringApplication.run(DemoConfigServerApplication.class, args);
    }

}
