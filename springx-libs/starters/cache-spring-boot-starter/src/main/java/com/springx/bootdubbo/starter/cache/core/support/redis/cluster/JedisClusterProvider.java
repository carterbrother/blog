package com.springx.bootdubbo.starter.cache.core.support.redis.cluster;


import com.springx.bootdubbo.starter.cache.config.RedisConstants;
import com.springx.bootdubbo.starter.cache.core.support.redis.JedisProvider;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;


/**
 * 集群 redis服务提供者
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年11月23日
 * @Copyright (c) 2015, springx.com
 */
public class JedisClusterProvider implements JedisProvider<JedisCluster, BinaryJedisCluster> {

    private JedisCluster jedisCluster;
    private BinaryJedisCluster binaryJedisCluster;
    private String groupName;

    public JedisClusterProvider(String groupName, JedisPoolConfig jedisPoolConfig, String[] servers, int timeout) {
        this.groupName = groupName;
        Set<HostAndPort> nodes = this.parseHostAndPort(servers);
        jedisCluster = new JedisCluster(nodes, timeout, RedisConstants.MAX_RE_DIRECTIONS, jedisPoolConfig);
        binaryJedisCluster = new BinaryJedisCluster(nodes, timeout, RedisConstants.MAX_RE_DIRECTIONS, jedisPoolConfig);
    }

    private Set<HostAndPort> parseHostAndPort(String[] servers) {
        try {
            Set<HostAndPort> hostAndPortHashSet = new HashSet<>();

            for (String part : servers) {
                String[] ipAndPort = part.split(":");
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                hostAndPortHashSet.add(hap);
            }

            return hostAndPortHashSet;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public JedisCluster get() {
        return jedisCluster;
    }

    @Override
    public BinaryJedisCluster getBinary() {
        return binaryJedisCluster;
    }

    @Override
    public void release() {
    }

    @Override
    public void destroy() throws Exception {
        jedisCluster.close();
        binaryJedisCluster.close();
    }


    @Override
    public String mode() {
        return RedisConstants.MODE_CLUSTER;
    }

    @Override
    public String groupName() {
        return groupName;
    }

}