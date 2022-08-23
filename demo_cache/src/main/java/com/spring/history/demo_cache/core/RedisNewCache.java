package com.spring.history.demo_cache.core;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author relax
 * 
 * @Email songqinghulove@163.com
 * 
 * @version Created on 2016-4-14下午3:20:42
 * 
 * @Describe 本地Redis缓存
 */
@Component
public class RedisNewCache extends RedisOperate {

	@Override
	protected RedisTemplate<String, Object> getRedisTemplate() {
		return JedisFactory.getInstance().getNew();
	}
}
