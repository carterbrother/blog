package com.springbootpractice.demo.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * 说明：代码方式配置缓存管理器
 * @author carter
 * 创建时间： 2020年01月21日 7:00 下午
 **/
@Configuration
public class RedisConfig {

    @Autowired private RedisTemplate redisTemplate;


    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheWriter redisWrite = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        configuration.prefixKeysWith("_demo_redis_");
        configuration.entryTtl(Duration.ofMinutes(10));
        configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisWrite,configuration);

        return redisCacheManager;
    }
}
