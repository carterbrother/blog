package com.springbootpractice.demo.p6spy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class DemoP6spyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoP6spyApplication.class, args);
    }

}
