package com.spring.story.demo.cache.pre;

import com.spring.story.demo.cache.pre.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * OOM问题定位
 * @author Carter.li
 */
@SpringBootApplication
public class OOMApplication implements CommandLineRunner {
    @Autowired
    FooService fooService;
    public static void main(String[] args) {
        SpringApplication.run(OOMApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        //程序启动后，不断调用Fooservice.oom()方法
        while (true) {
            fooService.oom();
        }
    }
}
