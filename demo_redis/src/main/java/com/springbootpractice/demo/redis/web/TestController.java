package com.springbootpractice.demo.redis.web;

import com.springbootpractice.demo.redis.listener.RedisListenerConfig;
import com.springbootpractice.demo.redis.lua.LuaScript;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 说明：测试redis的接口
 * @author carter
 * 创建时间： 2020年01月21日 6:22 下午
 **/
@RestController
public class TestController {

    private final StringRedisTemplate stringRedisTemplate;

    public TestController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping(path = "/send/{message}")
    public void publishMessage(@PathVariable("message") String message){
        stringRedisTemplate.convertAndSend(RedisListenerConfig.MY_CHANNEL,message);
    }

    @GetMapping(path = "/lua/{k1}/{v1}/{k2}/{v2}")
    public Long publishMessage(@PathVariable("k1") String k1,@PathVariable("k2") String k2,@PathVariable("v1") String v1,@PathVariable("v2") String v2){
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(LuaScript.lua1);
        redisScript.setResultType(Long.class);

        return stringRedisTemplate.execute(redisScript, Arrays.asList(k1, k2), v1, v2);
    }
}
