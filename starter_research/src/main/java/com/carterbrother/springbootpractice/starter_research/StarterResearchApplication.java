package com.carterbrother.springbootpractice.starter_research;

import com.carterbrother.springbootpractice.starter_research.use.RedisTemplateUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class StarterResearchApplication {



    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(StarterResearchApplication.class, args);

        final StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);

        redisTemplate.opsForValue().set("1","1111");

        RedisTemplateUseCase redisTemplateUseCase = context.getBean(RedisTemplateUseCase.class);
        redisTemplateUseCase.useCases();

    }

}
