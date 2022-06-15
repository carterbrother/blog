package com.lifesense.springboot.starter.redis.config;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 常量
 * @date 2019年02月25日 11:06 AM
 * @Copyright (c) carterbrother
 */
public class RedisConstants {

    private RedisConstants(){
        throw new AssertionError("No com.lifesense.springboot.starter.redis.config.RedisConstants instances for you!");
    }

    public static final String MODE_STANDARD="standard";

    public static final String MODE_CLUSTER="cluster";

    public static final String STRING_SPLIT_FLAG=",; \t\n";

    /**
     * 重试3次
     */
    public static final int MAX_RE_DIRECTIONS = 3;

    public static final String DEFAULT_GROUP_NAME = "default";

}
