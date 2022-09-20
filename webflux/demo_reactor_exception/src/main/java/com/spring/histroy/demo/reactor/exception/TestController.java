package com.spring.histroy.demo.reactor.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class TestController {


    @GetMapping("/getFluxRes")
    public List<String> getFluxRes(){
        Flux<Integer> integerFlux = Flux.range(1,10);

        List<String> stringList = new CopyOnWriteArrayList<>();
        integerFlux.subscribe(i->stringList.add("number "+i));

        return stringList;
    }



}
