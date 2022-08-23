package com.spring.history.demo_cache.core;

import org.springframework.beans.factory.annotation.Autowired;
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
public class RedisLocalCache extends RedisOperate {
	
	@Autowired
	RedisCache commonCache;

	@Override
	protected RedisTemplate<String, Object> getRedisTemplate() {
		return JedisFactory.getInstance().getLocal();
	}
	
	/**
	 * 获取Int 值
	 * @param key key
	 * @param def 默认值
	 * @param seconds 若本地缓存不存在，这从comon 缓存查询 存入本地缓存的时长
	 * @return
	 */
	public int getInt(String key,int def,int seconds) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			int item = commonCache.get(key, def);
			set(key, (Integer)item, seconds);
			
			return def;
		}
		return Integer.parseInt(value.toString());
	}
	
	/**
	 * 获取float 值
	 * @param key key
	 * @param def 默认值
	 * @param seconds 若本地缓存不存在，这从comon 缓存查询 存入本地缓存的时长
	 * @return
	 */
	public float getFloat(String key,float def,int seconds) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			float item = commonCache.get(key, def);
			set(key, (Float)item, seconds);
			return def;
		}
		return Float.parseFloat(value.toString());
	}
	
	/**
	 * 获取String 值
	 * @param key key
	 * @param def 默认值
	 * @param seconds 若本地缓存不存在，这从comon 缓存查询 存入本地缓存的时长
	 * @return
	 */
	public String getString(String key,String def,int seconds) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			String item = commonCache.get(key);
			set(key, item == null ? def : item, seconds);
			return item;
		}
		return value.toString();
	}
}
