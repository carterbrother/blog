package com.carterbrother.springbootpractice.starter_research.use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月27日 2:15 PM
 * @Copyright (c) carterbrother
 */
@Service
public class RedisTemplateUseCase {

    private static final Logger logger = LoggerFactory.getLogger(RedisTemplateUseCase.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public void useCases(){
        useStringRedisTemplate();
        useXXOperation();
        useBoundOperation();
    }

    private void useBoundOperation() {

        logger.info("bound    start");

        final BoundValueOperations boundValueOps = redisTemplate.boundValueOps("key3");
        boundValueOps.set("key3value");
        boundValueOps.append("|aaa");
        boundValueOps.expire(10000, TimeUnit.SECONDS);

        logger.info("bound    complete");

    }

    /**
     * 单key，单次操作
     */
    private void useXXOperation() {

        redisTemplate.opsForValue().set("key2","value2");
        redisTemplate.opsForHash().put("hashkey2","ff1","aaaa");
        redisTemplate.opsForSet().add("setkey1","a","b","c");
        redisTemplate.opsForList().rightPush("listkey1","aaaa");

        redisTemplate.opsForZSet().add("zsetkey1","fuck",100d);
        redisTemplate.opsForZSet().add("zsetkey1","fuck",101d);

        redisTemplate.opsForGeo().add("geoKey",new RedisGeoCommands.GeoLocation("a",new Point(0,0)));
        redisTemplate.opsForHyperLogLog().add("hyperLogKey1","aaa","bbb");

//        redisTemplate.opsForCluster().flushDb(RedisClusterNode.newRedisClusterNode().build());

    }

    private void useStringRedisTemplate() {

        /**
         * 两个操作是在两条不同的连接上的
         */
        redisTemplate.opsForValue().set("k1","k1_value");
        redisTemplate.opsForHash().put("hashkey1","f1", "xxxx");

    }



}
