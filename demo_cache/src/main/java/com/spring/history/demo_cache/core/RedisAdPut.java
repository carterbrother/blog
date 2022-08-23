package com.spring.history.demo_cache.core;

import com.spring.history.demo_cache.core.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 投放专用redis
 * 投放自回传接收点击的数据量庞大且每条点击会分设备号存多条,很占内存,所以单独一台redis存储
 * @author Administrator
 *
 */
@Component
public class RedisAdPut extends RedisOperate {
	@Autowired
	RedisCache cache;

	@Override
	protected RedisTemplate<String, Object> getRedisTemplate() {
		return JedisFactory.getInstance().getAdPut();
	}
	/**
	 * 设置多少天过期时间
	 * @param days 天数
	 */
	public void expireDays(String key, int days) {
		int seconds = DateUtils.getSecondsByDay(days);
		super.expire(key, seconds);
	}
	@Override
	public <T> T index(String key, long index, Class<T> clz) {
		Object value = super.index(key, index, clz);
		if(value==null) {
			value = cache.index(key, index, clz);
		}
		return (T) value;
	}
	@Override
	public String get(String key) {
		String value = super.get(key);
		if(value==null) {
			value = cache.get(key);
		}
		return value;
	}
	@Override
	public long ttl(String key) {
		long ttl = super.ttl(key);
		if(ttl<=0) {
			ttl = cache.ttl(key);
		}
		return ttl;
	}
	@Override
	public <T> List<T> allList(String key,Class<T> clz){
		List<T> value = super.allList(key, clz);
		if(value==null || value.size()==0) {
			value = cache.allList(key, clz);
		}
		return value;
	}
}
