package com.spring.history.demo_cache.core.redisson;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * @Date 2021/1/29 16:36
 * @Author by Quine
 */
public class RedissonManager {

    private static Config config = null ;
    //声明redisso对象
    private static Redisson redisson = null;


    public static void createRedisson(JSONObject redisConfig){
        String host = redisConfig.getString("common.redis.host");
        String port = redisConfig.getString("common.redis.port");
        String password = redisConfig.getString("common.redis.passwd");

        config = new org.redisson.config.Config();
        if (StringUtils.isEmpty(password)){
            config.useSingleServer().setAddress(host+":"+port);
        }else {
            config.useSingleServer().setAddress(host+":"+port).setPassword(password);
        }
        redisson = (Redisson) Redisson.create(config);
    }

    public static Redisson getRedisson(){
        return redisson;
    }
}
