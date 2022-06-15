package com.springbootpractice.demo.data.dict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoDataDictApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDataDictApplication.class, args);
	}

}
