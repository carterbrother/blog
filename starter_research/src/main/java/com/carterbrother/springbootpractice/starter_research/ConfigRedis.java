package com.carterbrother.springbootpractice.starter_research;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Redis配置的例子
 * @date 2019年02月27日 2:16 PM
 * @Copyright (c) carterbrother
 */
@Configuration
public class ConfigRedis {


    @Bean
    public RedisConnectionFactory redisConnectionFactory(){

        String ip = "127.0.0.1";
        int port = 6379;
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(ip,port);
        serverConfig.setPassword(RedisPassword.of("123456"));

        long timeout = 1000L;
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeout))
                        .build();
        RedisConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(serverConfig,clientConfig);

        return redisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getStringSerializer());

        return redisTemplate;
    }


}
