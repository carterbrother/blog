package com.springbootpractice.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@SpringBootApplication
public class DemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder){

        Function<GatewayFilterSpec, UriSpec> setHeaderFn = f -> {
            f.addRequestHeader("hello", "world");
            return f;
        };

        Function<GatewayFilterSpec, UriSpec> hystrixFn = f->{
            f.hystrix(config->config.setName("mycmd").setFallbackUri("forward:/fallback"));
            return f;
        };
        return builder.routes()
                .route(p-> p.path("/get").filters(setHeaderFn).uri("http://www.authine.com"))
                .route(p->p.host("*.hystrix.com").filters(hystrixFn).uri("http://www.baidu.com"))
                .build();
    }



    @RequestMapping("fallback")
    public Mono<String> fallback(){
        return Mono.just("fallback");
    }


    @RequestMapping("/delay/{sec}")
    public Mono<String> delay(@PathVariable("sec") Integer sec){

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Mono.just("fallback");
    }

}
