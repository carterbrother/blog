package com.carter.demo.classload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class DemoClassloadApplication {

	public static void main(String[] args) {

		//方便测试

		if (Objects.isNull(args) || args.length<1){
			System.setProperty("main.jar.path","d:/demo.jar");
		}

		SpringApplication.run(DemoClassloadApplication.class, args);
	}

}
