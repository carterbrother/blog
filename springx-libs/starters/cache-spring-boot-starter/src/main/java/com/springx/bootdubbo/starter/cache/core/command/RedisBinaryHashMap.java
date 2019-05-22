package com.springx.bootdubbo.starter.cache.core.command;

import redis.clients.util.SafeEncoder;

import java.util.*;

import static com.springx.bootdubbo.starter.cache.core.support.redis.JedisProviderFactory.*;


/**
 * redis操作hashmap
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年12月7日
 * @Copyright (c) 2015, springx.com
 */
public class RedisBinaryHashMap extends RedisCollection {

    public RedisBinaryHashMap(String key) {
        super(key);
    }

    /**
     * @param key
     * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
     */
    public RedisBinaryHashMap(String key, long expireTime) {
        super(key, expireTime);
    }

    /**
     * 指定组名
     *
     * @param key
     * @param groupName
     */
    public RedisBinaryHashMap(String key, String groupName) {
        super(key, groupName);
    }

    /**
     * @param key
     * @param groupName  分组名
     * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
     */
    public RedisBinaryHashMap(String key, String groupName, long expireTime) {
        super(key, groupName, expireTime);
    }

    /**
     * 设置hash缓存
     *
     * @param datas
     * @return
     */
    public boolean set(Map<String, byte[]> datas) {
        if (datas == null || datas.isEmpty()) {
            return false;
        }
        Map<byte[], byte[]> newDatas = new HashMap<>();
        Set<String> keySet = datas.keySet();
        for (String key : keySet) {
            if (datas.get(key) == null) {
                continue;
            }
            newDatas.put(SafeEncoder.encode(key), datas.get(key));
        }

        boolean result = false;
        try {
            if (isCluster(groupName)) {
                result = getBinaryJedisClusterCommands(groupName).hmset(key, newDatas).equals(RESP_OK);
            } else {
                result = getBinaryJedisCommands(groupName).hmset(key, newDatas).equals(RESP_OK);
            }
            //设置超时时间
            if (result) setExpireIfNot(expireTime);
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }

    }

    /**
     * 获取所有值
     * value以byte[]数组形式返回
     *
     * @return
     */
    public Map<String, byte[]> getAll() {
        try {
            Map<byte[], byte[]> datas = null;
            Map<String, byte[]> result = new HashMap<>();
            if (isCluster(groupName)) {
                datas = getBinaryJedisClusterCommands(groupName).hgetAll(key);
            } else {
                datas = getBinaryJedisCommands(groupName).hgetAll(key);
            }

            Iterator<Map.Entry<byte[], byte[]>> it = datas.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<byte[], byte[]> entry = it.next();
                result.put(SafeEncoder.encode(entry.getKey()), entry.getValue());
            }
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }

    }

    /**
     * 查看缓存hash是否包含某个key
     *
     * @param field
     * @return
     */
    public boolean containsKey(String field) {
        try {
            if (isCluster(groupName)) {
                return getBinaryJedisClusterCommands(groupName).hexists(key, SafeEncoder.encode(field));
            } else {
                return getBinaryJedisCommands(groupName).hexists(key, SafeEncoder.encode(field));
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 设置ç
     *
     * @param field
     * @param value
     * @return
     */
    public boolean set(String field, byte[] value) {
        boolean result = false;
        if (value == null) {
            return false;
        }
        //返回值（1:新字段被设置,0:已经存在值被更新）
        try {
            if (isCluster(groupName)) {
                result = getBinaryJedisClusterCommands(groupName)
                        .hset(key, SafeEncoder.encode(field), value) >= 0;
            } else {
                result = getBinaryJedisCommands(groupName).hset(key, SafeEncoder.encode(field), value) >= 0;
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

    /**
     * 设置ç
     *
     * @param field
     * @param value
     * @return
     */
    public boolean set(String field, Object value) {
        boolean result = false;
        if (value == null) {
            return false;
        }
        //返回值（1:新字段被设置,0:已经存在值被更新）
        try {
            if (isCluster(groupName)) {
                result = getBinaryJedisClusterCommands(groupName)
                        .hset(key, SafeEncoder.encode(field), SafeEncoder.encode(value.toString())) >= 0;
            } else {
                result = getBinaryJedisCommands(groupName).hset(key, SafeEncoder.encode(field), SafeEncoder.encode(value.toString())) >= 0;
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

    /**
     * 移除hash缓存中的指定值
     *
     * @param field
     * @return
     */
    public boolean remove(String field) {
        try {
            if (isCluster(groupName)) {
                return getBinaryJedisClusterCommands(groupName).hdel(key, SafeEncoder.encode(field)).equals(RESP_OK);
            } else {
                return getBinaryJedisCommands(groupName).hdel(key, SafeEncoder.encode(field)).equals(RESP_OK);
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 返回长度
     *
     * @return
     */
    public long length() {
        try {
            if (isCluster(groupName)) {
                return getBinaryJedisClusterCommands(groupName).hlen(key);
            } else {
                return getBinaryJedisCommands(groupName).hlen(key);
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 获取一个值
     *
     * @param field
     * @return
     */
    @SuppressWarnings("unchecked")
    public byte[] getOne(String field) {
        return get(field).get(field);
    }

    /**
     * 获取多个key的值
     *
     * @param fields
     * @return
     */
    public Map<String, byte[]> get(String... fields) {
        try {
            List<byte[]> datas = null;
            Map<String, byte[]> result = new HashMap<>();
            for (String field : fields) {
                if (isCluster(groupName)) {
                    datas = getBinaryJedisClusterCommands(groupName).hmget(key, SafeEncoder.encode(field));
                } else {
                    datas = getBinaryJedisCommands(groupName).hmget(key, SafeEncoder.encode(field));
                }
                result.put(field, datas.get(0));
            }
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }

    }

}
