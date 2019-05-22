package com.springx.bootdubbo.starter.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description redis的属性配置对象
 * @date 2019年02月25日 10:35 AM
 * @Copyright (c) carterbrother
 */
@ConfigurationProperties(prefix = "redis.config")
public class RedisPropertiesConfig {
    /**
     * mode(standard:标准模式,cluster:集群模式,shard:分片模式)
     */
    private String mode;
    /**
     * redis的服务器配置，多个服务器请使用，分隔开
     */
    private String servers;
    /**
     * 连接超时时间
     */
    private Integer connTimeout;
    /**
     * 最大连接数
     */
    private Integer poolMaxTotal;
    /**
     * 最大空闲连接数
     */
    private Integer poolMaxIdle;
    /**
     * 最小空闲连接数
     */
    private Integer poolMinIdle;
    /**
     * 所有的Key的前缀
     */
    private String keyPrefix;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public Integer getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(Integer connTimeout) {
        this.connTimeout = connTimeout;
    }

    public Integer getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public void setPoolMaxTotal(Integer poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public Integer getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(Integer poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }

    public Integer getPoolMinIdle() {
        return poolMinIdle;
    }

    public void setPoolMinIdle(Integer poolMinIdle) {
        this.poolMinIdle = poolMinIdle;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
