package com.springbootpractice.demo.demo_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoRestApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoRestApplication.class, args);

        testGet();
    }


    public static void testGet(){
         RestTemplate restTemplate = new RestTemplate();

        String url = "https://crmapi.tianan-life.com/tiananuser_service/testNewUser/new/{plat}/{start}/{end}?requestId=x";
        Map<String, Object> params = new HashMap<>(3);
        params.put("plat",0);
        params.put("start","2019-12-20");
        params.put("end","2020-01-06");

        final ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class, params);

        System.out.println("responseEntity.getStatusCodeValue() : "+ responseEntity.getStatusCodeValue());
        System.out.println("responseEntity.getBody() : "+ responseEntity.getBody());


    }



}
