package com.springx.bootdubbo.starter.cache.core.command;

import com.springx.bootdubbo.starter.cache.core.CacheExpires;
import redis.clients.util.SafeEncoder;

import java.util.Date;
import java.util.Random;

import static com.springx.bootdubbo.starter.cache.core.support.redis.JedisProviderFactory.*;


/**
 * 对象redis操作对象（通过二进制序列化缓存）
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年12月7日
 * @Copyright (c) 2015, springx.com
 */
public class RedisObject extends RedisBase {


    /**
     * @param key
     */
    public RedisObject(String key) {
        super(key);
    }

    /**
     * 指定组名
     *
     * @param key
     * @param groupName
     */
    public RedisObject(String key, String groupName) {
        super(key, groupName);
    }

    /**
     * 重置key（适合一个方法里面频繁操作不同缓存的场景）<br>
     * <font color="red">非线程安全，请不要在多线程场景使用</font>
     *
     * @param key
     * @return
     */
    public RedisObject resetKey(String key) {
        key = prefixKey(key, this.groupName);
        this.origKey = key;
        this.key = SafeEncoder.encode(key);
        return this;
    }

    /**
     * 设置缓存，默认过期时间
     *
     * @param value
     * @return
     */
    public boolean set(Object value) {
        //避免某些缓存同时失效，加随机时长
        long expire = DEFAULT_EXPIRE_TIME + new Random(CacheExpires.IN_1DAY).nextLong();
        return set(value, expire);
    }

    /**
     * 设置缓存指定过期时间间隔
     *
     * @param value
     * @param seconds (过期秒数 ，小于等于0时 不设置)
     * @return
     */
    public boolean set(Object value, long seconds) {

        if (value == null) {
            return false;
        }
        try {
            boolean result = false;
            if (isCluster(groupName)) {
                result = getBinaryJedisClusterCommands(groupName).set(key, valueSerialize(value)).equals(RESP_OK);
            } else {
                result = getBinaryJedisCommands(groupName).set(key, valueSerialize(value)).equals(RESP_OK);
            }
            if (result) {
                result = setExpire(seconds);
            }
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }

    }

    /**
     * 检查给定 key 是否存在。
     *
     * @return
     */
    public boolean set(Object value, Date expireAt) {
        if (value == null) {
            return false;
        }
        try {
            boolean result = false;
            if (isCluster(groupName)) {
                result = getBinaryJedisClusterCommands(groupName).set(key, valueSerialize(value)).equals(RESP_OK);
                ;
            } else {
                result = getBinaryJedisCommands(groupName).set(key, valueSerialize(value)).equals(RESP_OK);
            }
            if (result) {
                result = setExpireAt(expireAt);
            }
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }
    }


    public <T> T get() {
        try {
            //本地缓存读取
//			T value = LocalCacheProvider.getInstance().get(this.origKey);
//			if(value != null){
//				return value;
//			}

            byte[] bytes = null;
            if (isCluster(groupName)) {
                bytes = getBinaryJedisClusterCommands(groupName).get(key);
            } else {
                bytes = getBinaryJedisCommands(groupName).get(key);
            }
            T value = valueDerialize(bytes);
            return value;
        } finally {
            getJedisProvider(groupName).release();
        }

    }


}
