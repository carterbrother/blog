package com.springbootpractice.demo.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class DemoResttemplateApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoResttemplateApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        testHeaderSet();
    }

    private void testGet() {
        //访问echo;
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://rest-beta.lifesense.com/saasuser_service/echo?requestId={requestId}")
                .build(UUID.randomUUID().toString());
        final String responseObject = restTemplate.getForObject(url, String.class);
        log.info("响应：{}", responseObject);
    }

    public void testHeaderSet(){

        URI url = UriComponentsBuilder
                .fromHttpUrl("http://rest-beta.lifesense.com/saasuser_service/echo")
                .build().toUri();

        RequestEntity requestEntity = new RequestEntity(HttpMethod.GET,url);
        requestEntity.getHeaders().add("requestId",UUID.randomUUID().toString());
        final ResponseEntity<String> responseObject = restTemplate.exchange(requestEntity, String.class);
        log.info("响应：{}", responseObject);

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
