package com.spring.history.demo_cache.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author relax
 * 
 * @Email songqinghulove@163.com
 * 
 * @version Created on 2016-4-14下午3:20:42
 * 
 * @Describe
 */
@Component
public class RedisDateCenterCache extends RedisOperate{
	
	@Autowired(required=false)
	@Qualifier(value="redisDataCenterTemplate")
	RedisTemplate<String, Object> redisTemplate;

	@Override
	protected RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}
	
	
}
