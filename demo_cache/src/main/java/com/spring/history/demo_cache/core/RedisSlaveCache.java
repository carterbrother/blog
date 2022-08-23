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
 * @Describe
 */
@Component
public class RedisSlaveCache extends RedisOperate {

//	@Autowired
//	RedisTemplate<String, Object> redisTemplate;

	@Override
	protected RedisTemplate<String, Object> getRedisTemplate() {
		return JedisFactory.getInstance().getSlave();
	}

}
