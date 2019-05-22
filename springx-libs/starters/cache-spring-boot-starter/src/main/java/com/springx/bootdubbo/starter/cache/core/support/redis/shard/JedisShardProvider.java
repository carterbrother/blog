package com.springx.bootdubbo.starter.cache.core.support.redis.shard;

import com.springx.bootdubbo.starter.cache.config.RedisConstants;
import com.springx.bootdubbo.starter.cache.core.support.redis.JedisProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 标准（单服务器）redis服务提供者
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年12月8日
 * @Copyright (c) 2015, springx.com
 */
public class JedisShardProvider implements JedisProvider<ShardedJedis, BinaryShardedJedis> {

    protected static final Logger logger = LoggerFactory.getLogger(JedisShardProvider.class);

    private ThreadLocal<ShardedJedis> context = new ThreadLocal<>();

    private ShardedJedisPool jedisPool;

    private String groupName;


    public JedisShardProvider(String groupName, JedisPoolConfig jedisPoolConfig, String[] servers, int timeout) {
        super();
        this.groupName = groupName;
        List<JedisShardInfo> shards = buildShardInfos(servers, timeout);
        jedisPool = new ShardedJedisPool(jedisPoolConfig, shards);
    }

    private List<JedisShardInfo> buildShardInfos(String[] servers, int timeout) {
        List<JedisShardInfo> infos = new ArrayList<>();
        for (String server : servers) {
            String[] addrs = server.split(":");
            JedisShardInfo info = new JedisShardInfo(addrs[0], Integer.parseInt(addrs[1].trim()), timeout);
            infos.add(info);
        }

        return infos;
    }

    @Override
    public ShardedJedis get() throws JedisException {
        ShardedJedis jedis = context.get();
        if (jedis != null) {
            return jedis;
        }
        try {
            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            if (jedis != null) {
                jedis.close();
            }
            throw e;
        }
        context.set(jedis);
        if (logger.isTraceEnabled()) {
            logger.trace(">>get a redis conn[{}]", jedis.toString());
        }
        return jedis;
    }

    @Override
    public BinaryShardedJedis getBinary() {
        return get();
    }

    @Override
    public void release() {
        ShardedJedis jedis = context.get();
        if (jedis != null) {
            context.remove();
            jedis.close();
            if (logger.isTraceEnabled()) {
                logger.trace("<<release a redis conn[{}]", jedis.toString());
            }
        }
    }


    @Override
    public void destroy() throws Exception {
        if (Objects.nonNull(jedisPool)) {
            jedisPool.destroy();
        }
    }


    @Override
    public String mode() {
        return RedisConstants.MODE_STANDARD;
    }

    @Override
    public String groupName() {
        return groupName;
    }

}
