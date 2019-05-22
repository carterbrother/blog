package com.springx.bootdubbo.starter.cache.core.command;


import com.springx.bootdubbo.common.util.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static com.springx.bootdubbo.starter.cache.core.support.redis.JedisProviderFactory.*;


/**
 * 数字redis操作命令
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年12月7日
 * @Copyright (c) 2015, springx.com
 */
public class RedisNumber extends RedisString {

    public RedisNumber(String key) {
        super(key);
    }

    public RedisNumber(String key, String groupName) {
        super(key, groupName);
    }

    public boolean set(Object value) {
        return super.set(value.toString());
    }

    public boolean set(Object value, long seconds) {
        return super.set(value.toString(), seconds);
    }

    public boolean set(Object value, Date expireAt) {
        return super.set(value.toString(), expireAt);
    }

    public Integer getInteger() {
        String value = super.get();
        return value == null ? null : StringUtil.toInt(value);
    }

    public Long getLong() {
        String value = super.get();
        return value == null ? null : StringUtil.toLong(value);
    }

    public Float getFloat() {
        String value = super.get();
        return value == null ? null : StringUtil.toFloat(value);
    }

    public Short getShort() {
        String value = super.get();
        return value == null ? null : StringUtil.toShort(value);
    }

    public Double getDouble() {
        String value = super.get();
        return value == null ? null : StringUtil.toDouble(value);
    }

    /**
     * @param scale 小数点位数
     * @return
     */
    public BigDecimal getBigDecimal(int scale) {
        String value = super.get();
        return value == null ? null : new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 指定key的值加操作
     *
     * @param integer
     * @return
     */
    @Deprecated
    public boolean increase(long integer) {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).incrBy(key, integer) == 1;
            } else {
                return getJedisCommands(groupName).incrBy(key, integer) == 1;
            }

        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 指定key的值减操作
     *
     * @param integer
     * @return
     */
    @Deprecated
    public boolean reduce(long integer) {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).decrBy(key, integer) == 1;
            } else {
                return getJedisCommands(groupName).decrBy(key, integer) == 1;
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 指定key的值加操作
     *
     * @param integer
     * @return
     */
    public long incrBy(long integer) {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).incrBy(key, integer);
            } else {
                return getJedisCommands(groupName).incrBy(key, integer);
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 指定key的值减操作
     *
     * @param integer
     * @return
     */
    public long decrBy(long integer) {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).decrBy(key, integer);
            } else {
                return getJedisCommands(groupName).decrBy(key, integer);
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

    /**
     * 指定key的值加操作
     *
     * @return
     */
    public double incrByFloat(double f) {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).incrByFloat(key, f);
            } else {
                return getJedisCommands(groupName).incrByFloat(key, f);
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

}
