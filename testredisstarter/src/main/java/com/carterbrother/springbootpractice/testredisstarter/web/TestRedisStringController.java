package com.carterbrother.springbootpractice.testredisstarter.web;

import com.lifesense.springboot.starter.redis.core.command.RedisString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月25日 4:55 PM
 * @Copyright (c) carterbrother
 */
@RestController
public class TestRedisStringController {

    @GetMapping("/redisString/set")
    public String redis_set(@RequestParam String key, @RequestParam String value , @RequestParam Integer expireAt){

        RedisString redisString = new RedisString(key);
        redisString.set(value,expireAt);

        return redisString.get();

    }
}
