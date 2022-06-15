package com.springbootpractice.demo.redis.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 说明：配置队列监听器，对应的主题
 * @author carter
 * 创建时间： 2020年01月21日 5:55 下午
 **/
@Configuration
public class RedisListenerConfig {

    public static final String MY_CHANNEL = "myChannel";
    private final MyRedisMessageListener myRedisMessageListener;
    private final MyRedisMessageListener2 myRedisMessageListener2;
    private final RedisTemplate redisTemplate;

    public RedisListenerConfig(MyRedisMessageListener myRedisMessageListener, MyRedisMessageListener2 myRedisMessageListener2, RedisTemplate redisTemplate) {
        this.myRedisMessageListener = myRedisMessageListener;
        this.myRedisMessageListener2 = myRedisMessageListener2;
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();

        redisMessageListenerContainer.setConnectionFactory(redisTemplate.getConnectionFactory());
        final ExecutorService taskExecutor = new ThreadPoolExecutor(1,
                2, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2000));
        redisMessageListenerContainer.setTaskExecutor(taskExecutor);

        final Topic myChannel = new ChannelTopic(MY_CHANNEL);

        redisMessageListenerContainer.addMessageListener(myRedisMessageListener, myChannel);
        redisMessageListenerContainer.addMessageListener(myRedisMessageListener2, myChannel);

        System.out.println("注册redis的消息队列成功！");
        return redisMessageListenerContainer;
    }

}
