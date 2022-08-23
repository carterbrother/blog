package com.spring.history.demo_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoCacheApplication {

	public static void main(String[] args) {
		System.setProperty("env","DEV");
		SpringApplication.run(DemoCacheApplication.class, args);
	}

}
