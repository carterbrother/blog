package com.springx.bootdubbo.starter.cache.core.command;

import java.util.HashSet;
import java.util.Set;

import static com.springx.bootdubbo.starter.cache.core.support.redis.JedisProviderFactory.*;


/**
 * 对象redis操作set
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年12月7日
 * @Copyright (c) 2015, springx.com
 */
public class RedisSet extends RedisCollection {

    public RedisSet(String key) {
        super(key);
    }

    /**
     * @param key
     * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
     */
    public RedisSet(String key, long expireTime) {
        super(key, expireTime);
    }

    /**
     * 指定组名
     *
     * @param key
     * @param groupName
     */
    public RedisSet(String key, String groupName) {
        super(key, groupName);
    }

    /**
     * @param key
     * @param groupName  分组名
     * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
     */
    public RedisSet(String key, String groupName, long expireTime) {
        super(key, groupName, expireTime);
    }

    public boolean add(Object... objects) {
        try {
            boolean result = false;
            byte[][] datas = valuesSerialize(objects);
            if (isCluster(groupName)) {
                result = getBinaryJedisClusterCommands(groupName).sadd(key, datas) >= 1;
            } else {
                result = getBinaryJedisCommands(groupName).sadd(key, datas) >= 1;
            }
            //设置超时时间
            if (result) {
                setExpireIfNot(expireTime);
            }
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    public <T> Set<T> get() {
        Set<byte[]> datas = null;
        try {
            if (isCluster(groupName)) {
                datas = getBinaryJedisClusterCommands(groupName).smembers(key);
            } else {
                datas = getBinaryJedisCommands(groupName).smembers(key);
            }
            return toObjectSet(datas);
        } finally {
            getJedisProvider(groupName).release();
        }
    }


    public boolean remove(Object... objects) {
        try {
            byte[][] datas = valuesSerialize(objects);
            if (isCluster(groupName)) {
                return getBinaryJedisClusterCommands(groupName).srem(key, datas) == 1;
            } else {
                return getBinaryJedisCommands(groupName).srem(key, datas) == 1;
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    public long length() {
        try {
            if (isCluster(groupName)) {
                return getBinaryJedisClusterCommands(groupName).scard(key);
            } else {
                return getBinaryJedisCommands(groupName).scard(key);
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    public boolean contains(Object object) {
        try {
            if (isCluster(groupName)) {
                return getBinaryJedisClusterCommands(groupName).sismember(key, valueSerialize(object));
            } else {
                return getBinaryJedisCommands(groupName).sismember(key, valueSerialize(object));
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }


    protected <T> Set<T> toObjectSet(Set<byte[]> datas) {
        Set<T> result = new HashSet<>();
        if (datas == null) {
            return result;
        }
        for (byte[] data : datas) {
            result.add(valueDerialize(data));
        }
        return result;
    }
}
