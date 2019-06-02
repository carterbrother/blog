package com.springx.demoservice;

import com.springx.bootdubbo.starter.rest.config.RestPropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lifesense-szyf01
 */
@SpringBootApplication
@Slf4j
public class Application {

    @Autowired
    private RestPropertiesConfig restPropertiesConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
