package com.springbootpractice.demo.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

/**
 * 说明：redis的监听器
 * @author carter
 * 创建时间： 2020年01月21日 5:51 下午
 **/
@Component
public class MyRedisMessageListener2 implements org.springframework.data.redis.connection.MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {

        System.out.println("MyRedisMessageListener2 topic:"+new String(pattern) +" 消息："+ new String(message.getBody()));

    }
}
