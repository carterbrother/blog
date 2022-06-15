package com.lifesense.springboot.starter.redis.core.support.redis.standard;

import com.lifesense.springboot.starter.redis.config.RedisConstants;
import com.lifesense.springboot.starter.redis.core.support.redis.JedisProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Objects;


/**
 *
 * @description 标准（单服务器）redis服务提供者>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月8日
 * @Copyright (c) 2015, lifesense.com
 */
public class JedisStandardProvider implements JedisProvider<Jedis,BinaryJedis> {
	
	protected static final Logger logger = LoggerFactory.getLogger(JedisStandardProvider.class.getName());

	private ThreadLocal<Jedis> context = new ThreadLocal<>();
	
	private JedisPool jedisPool;
	
	private String groupName;
	

	public JedisStandardProvider(String groupName,JedisPoolConfig jedisPoolConfig, String[] servers, int timeout) {
		super();
		this.groupName = groupName;
		String[] addrs = servers[0].split(":");
		jedisPool = new JedisPool(jedisPoolConfig, addrs[0], Integer.parseInt(addrs[1].trim()), timeout);
	}

	@Override
	public Jedis get() throws JedisException {
		Jedis jedis = context.get();
		if (jedis != null) {
			return jedis;
		}
		try {
			jedis = jedisPool.getResource();
		} catch (JedisException e) {
			if (Objects.nonNull(jedis)) {
				jedis.close();
			}
			throw e;
		}
		context.set(jedis);
		if (logger.isTraceEnabled()) {
			logger.trace(">>get a redis conn[{}],Host:{}", jedis.toString(), jedis.getClient().getHost());
		}
		return jedis;
	}
 
	@Override
	public BinaryJedis getBinary() {	
		return get();
	}

	@Override
	public void release() {
		Jedis jedis = context.get();
        if (Objects.nonNull(jedis)) {
        	context.remove();
        	jedis.close();
        	if(logger.isTraceEnabled()){
            	logger.trace("<<release a redis conn[{}]",jedis.toString());
            }
        }
    }

	
	@Override
	public void destroy() throws Exception{
		if (Objects.nonNull(jedisPool)){
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
