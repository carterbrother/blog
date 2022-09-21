package com.spring.story.webflux.a.test;

import com.spring.story.webflux.a.dto.Greeting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GreetingClient {


    public Mono<String> getMessage(){
        return WebClient.builder().baseUrl("http://localhost:7000").build().get().uri("/hello").accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }

}
