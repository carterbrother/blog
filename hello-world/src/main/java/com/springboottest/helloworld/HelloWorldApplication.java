package com.springboottest.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Value("${server.port:0}")
	private Long serverPort;

	@RequestMapping("/hello")
	public String helloWorld(){
		return "hello world ".concat(String.valueOf(serverPort));
	}

}
