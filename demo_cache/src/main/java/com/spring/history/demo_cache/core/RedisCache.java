package com.spring.history.demo_cache.core;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.spring.history.demo_cache.core.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 主redis
 * @author relax
 * 
 * @Email songqinghulove@163.com
 * 
 * @version Created on 2016-4-14下午3:20:42
 * 
 * @Describe
 */
@Component
public class RedisCache extends RedisOperate {

	@Autowired
	RedisNewCache newCache;
	
	Config config = ConfigService.getConfig("PF.common-values");
	
	
	@Override
	protected RedisTemplate<String, Object> getRedisTemplate() {
		return JedisFactory.getInstance().getCommon();
	}
	
	@Override
	public void set(String key, long index, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.set(key, index, value);
		
		super.set(key, index, value);
	}
	@Override
	public void set(String key, Object value, int seconds) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.set(key, value, seconds);
		super.set(key, value, seconds);
	}
	@Override
	public boolean setnx(String key, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.setnx(key, value);
		return super.setnx(key, value);
	}
	@Override
	public void setnx(String key, Object value, int seconds) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.setnx(key, value, seconds);
		super.setnx(key, value, seconds);
	}
	@Override
	public void set(String key, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.set(key, value);
		super.set(key, value);
	}
	@Override
	public double increment(String key, double delta) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.increment(key, delta);
		return super.increment(key, delta);
	}
	@Override
	public long increment(String key, long delta) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.increment(key, delta);
		return super.increment(key, delta);
	}
	@Override
	public void delete(String key) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.delete(key);
		super.delete(key);
	}
	@Override
	public long expire(String key, int seconds) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.expire(key, seconds);
		return super.expire(key, seconds);
	}
	/**
	 * 设置多少天过期时间
	 * @param days 天数
	 */
	public void expireDays(String key, int days) {
		int seconds = DateUtils.getSecondsByDay(days);
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.expire(key, seconds);
		super.expire(key, seconds);
	}
	@Override
	public long expireAt(String key, Date date) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.expireAt(key, date);
		return super.expireAt(key, date);
	}
	@Override
	public Long push(String key, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.push(key, value);
		return super.push(key, value);
	}
	@Override
	public <T> Long pushAll(String key, List<T> valuse) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.pushAll(key, valuse);
		return super.pushAll(key, valuse);
	}

	@Override
	public List<String> removeList(final String key, final int start, final int end) {
		List<String> list = super.removeList(key, start, end);
		if (list!=null && list.size()>0){
			if(config.getIntProperty("newRedis", 0)==1)
				newCache.trim(key,end+1,-1);
		}
		return list;
	}


	@Override
	public Set<Object> removeZSet(String lockName , String key, double min, double max ) {

		Set<Object> objects = super.removeZSet(lockName, key, min, max);
		if (objects.size()>0){
			if(config.getIntProperty("newRedis", 0)==1)
				newCache.remove(key,objects);
		}
		return objects;
	}


	@Override
	public void zadd(String key, Object value,double score) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.zadd(key, value,score);
		super.zadd(key, value,score);
	}

	@Override
	public Object pop(String key) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.pop(key);
		return super.pop(key);
	}
	@Override
	public Long in(String key, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.in(key, value);
		return super.in(key, value);
	}
	@Override
	public <T> T out(String key, Class<T> clz) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.out(key, clz);
		return super.out(key, clz);
	}
	@Override
	public void remove(String key, long i, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.remove(key, i, value);
		super.remove(key, i, value);
	}
	@Override
	public void hmset(String key, Map<String, Object> values) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.hmset(key, values);
		super.hmset(key, values);
	}
	@Override
	public void hmset(String key, String hashKey, Object value) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.hmset(key, hashKey, value);
		super.hmset(key, hashKey, value);
	}
	@Override
	public void hmDel(String key, Object... hashKeys) {
		if(config.getIntProperty("newRedis", 0)==1)
			newCache.hmDel(key, hashKeys);
		super.hmDel(key, hashKeys);
	}
	
}
