package com.spring.history.demo_cache.core;

import com.alibaba.fastjson.JSONObject;
import com.spring.history.demo_cache.core.redisson.DistributedRedisLock;
import com.spring.history.demo_cache.core.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author relax
 * @since jdk1.7,Created on 2017-3-22 下午2:54:08
 * @version 1.0
 **/
public abstract class RedisOperate {
	
	/** 一天秒数 */
	public static final int DAY = 86400;
	
	protected abstract RedisTemplate<String, Object> getRedisTemplate();

	Logger logger = LoggerFactory.getLogger("RedisOperate");
	
	public void channelSend(String channel, JSONObject msg) {
		getRedisTemplate().convertAndSend(channel, msg.toJSONString());
	}

	/**
	 * 向缓存中设置字符串内容
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 * @throws Exception
	 */
	public void set(String key, Object value) {
		ValueOperations<String, Object> valueOperations = getRedisTemplate().opsForValue();
		valueOperations.set(key, value.toString());
	}

	/**
	 * 向缓存中设置字符串内容
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 * @throws Exception
	 */
	public void set(String key, Object value, int seconds) {
		if(seconds<=0) {//23:59:59取当天剩余时间剩0秒,插入会报错
			return;
		}
		ValueOperations<String, Object> valueOperations = getRedisTemplate().opsForValue();
		valueOperations.set(key, value.toString(), seconds, TimeUnit.SECONDS);
	}

	/**
	 * 当key不存在的时候 进行设值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setnx(String key, Object value) {
		ValueOperations<String, Object> valueOperations = getRedisTemplate()
				.opsForValue();
		return valueOperations.setIfAbsent(key, value.toString());
	}

	/**
	 * 当key不存在的时候 进行设值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void setnx(String key, Object value, int seconds) {
		ValueOperations<String, Object> valueOperations = getRedisTemplate()
				.opsForValue();
		valueOperations.setIfAbsent(key, value.toString());
		if (seconds >= 0)
			expire(key, seconds);
	}

	/**
	 * 获取键值对象
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return (T) value;
	}

	/**
	 * 获取键值对象
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	public JSONObject getJson(String key) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return JSONObject.parseObject(value.toString());
	}

	/**
	 * 获取键值对象
	 * 
	 * @param key
	 *            键
	 * @param defalut
	 *            默认值
	 * @return
	 */
	public int get(String key, int defalut) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			return defalut;
		}
		return Integer.parseInt(value.toString());
	}

	/**
	 * 获取键值对象
	 * 
	 * @param key
	 *            键
	 * @param defalut
	 *            默认值
	 * @return
	 */
	public float get(String key, float defalut) {
		Object value = getRedisTemplate().opsForValue().get(key);
		if (value == null) {
			return defalut;
		}
		return Float.parseFloat(value.toString());
	}

	/**
	 * 在原有的键/值上进行原子追加
	 * 
	 * @param key
	 * @param delta
	 * @return
	 */
	public long increment(String key, long delta) {
		long value = getRedisTemplate().opsForValue().increment(key, delta);
		return value;
	}

	/**
	 * 在原有的键/值上进行原子追加
	 * 
	 * @param key
	 * @param delta
	 * @return
	 */
	public double increment(String key, double delta) {
		double value = getRedisTemplate().opsForValue().increment(key, delta);
		return value;
	}

	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return 删除成功true 失败false
	 */
	public void delete(String key) {
		getRedisTemplate().delete(key);
	}

	/**
	 * 设置一个key的过期时间（单位：秒）
	 * 
	 * @param key
	 *            key值
	 * @param seconds
	 *            多少秒后过期
	 * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
	 */
	public long expire(String key, int seconds) {
		if (key == null || key.equals("")) {
			return 0;
		}

		getRedisTemplate().expire(key, seconds, TimeUnit.SECONDS);

		return 0;
	}

	/**
	 * 设置一个key在某个时间点过期
	 * 
	 * @param key
	 *            key值
	 * @param date
	 *            unix时间戳，从1970-01-01 00:00:00开始到现在的秒数
	 * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
	 */
	public long expireAt(String key, Date date) {
		getRedisTemplate().expireAt(key, date);
		return 0;
	}

	/**
	 * List 移除指定范围List 移除指定范围
	 * @param key
	 * @param start
	 * @param end
	 * @param <T>
	 * @return
	 */
	public <T> T removeList(final String key, final int start, final int end) {

        SessionCallback<Object> callback = new SessionCallback<Object>() {

            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForList().range(key, start, end);
                operations.opsForList().trim(key,end+1,-1);
                List exec = operations.exec();
                return exec;
            }
        };
        List<T> execute = (List<T>)getRedisTemplate().execute(callback);
        return execute.get(0);
	}

	/**
	 * 压栈
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long push(String key, Object value) {
		return getRedisTemplate().opsForList().leftPush(key, value);
	}

	/**
	 * 压栈
	 * 
	 * @param key
	 * @param valuse
	 * @return
	 */
	public <T> Long pushAll(String key, List<T> valuse) {
		int len = valuse == null ? 0 : valuse.size();
		Object[] items = new Object[len];
		for (int i = 0; i < len; i++) {
			items[i] = valuse.get(i);
		}
		return getRedisTemplate().opsForList().leftPushAll(key, items);
	}

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public Object pop(String key) {
		Object item = getRedisTemplate().opsForList().leftPop(key);
		return item;
	}
	public Object rpop(String key) {
		Object item = getRedisTemplate().opsForList().rightPop(key);
		return item;
	}

	/**
	 * 入队
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long in(String key, Object value) {
		return getRedisTemplate().opsForList().rightPush(key, value);
	}

	/**
	 * 出队
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T out(String key, Class<T> clz) {
		Object item = getRedisTemplate().opsForList().leftPop(key);
		return (T) item;
	}

	/**
	 * 栈/队列长
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) {
		return getRedisTemplate().opsForList().size(key);
	}

	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> range(String key, int start, int end, Class<T> clz) {
		List<Object> values = getRedisTemplate().opsForList().range(key, start, end);
		if (values == null)
			return null;
		return (List<T>) values;
	}
	/**
	 * 获取list列表所有值,0到-1表示所有
	 * @param key
	 * @param clz
	 * @return
	 */
	public <T> List<T> allList(String key,Class<T> clz){
		int start = 0;
		int end = -1;
		return range(key, start, end, clz);
	}

	/**
	 * 移除
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, Object value) {
		getRedisTemplate().opsForList().remove(key, i, value);
	}

	/**
	 * 检索
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T index(String key, long index, Class<T> clz) {
		Object value = getRedisTemplate().opsForList().index(key, index);
		if (value == null)
			return null;
		return (T) value;
	}

	/**
	 * 置值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, Object value) {
		getRedisTemplate().opsForList().set(key, index, value);
	}

	/**
	 * 裁剪
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		getRedisTemplate().opsForList().trim(key, start, end);
	}

	public void hmset(String key, Map<String, Object> values) {
		getRedisTemplate().opsForHash().putAll(key, values);
	}
	
	/**
	 * 新增
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hmset(String key, String hashKey,Object value) {
		getRedisTemplate().opsForHash().put(key, hashKey, value);
	}
	
	/**
	 * 批量删除key
	 * @param key
	 * @param hashKeys
	 */
	public void hmDel(String key, Object...hashKeys) {
		getRedisTemplate().opsForHash().delete(key, hashKeys);
	}
	
	public List<Object> hValues(String key) {
		return getRedisTemplate().opsForHash().values(key);
	}
	/**
	 * 获取hash数值的总和 
	 */
	public int hValuesSum(String key) {
		List<Object> list = hValues(key);
		int sum = 0;
		for(Object value:list) {
			sum+=Integer.parseInt(String.valueOf(value));
		}
		return sum;
	}

	public boolean hasKey(String key) {
		return getRedisTemplate().hasKey(key);
	}

	public Map<Object, Object> entries(String key) {
		return getRedisTemplate().opsForHash().entries(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T hget(String key, String hashKey, Class<T> clz) {
		Object item = getRedisTemplate().opsForHash().get(key, hashKey);
		if (item == null)
			return null;
		return (T) item;
	}
	
	public int hget(String key, String hashKey, int defalut) {
		Object item = getRedisTemplate().opsForHash().get(key, hashKey);
		if (item == null)
			return defalut;
		return Integer.parseInt(item.toString());
	}
	public long ttl(String key) {
		return getRedisTemplate().getExpire(key);
	}
	public void setRedisByReset(String key,Object data,String reset){
		long expireTime = 0;
    	switch(reset){
    		case "everyMonth":
    			String format = DateUtils.FORMAT_ONE;
    			String nextMonth = DateUtils.getNextMonth(format);
    			expireTime=DateUtils.strtotime(nextMonth,format);
    			break;
    		default:return;
    	}
    	long leftTime = expireTime - DateUtils.time();
    	set(key,data,(int)leftTime);
    }
	/**
	 * hashMap 自增
	 * @param key
	 * @param hashKey
	 * @param delta
	 */
	public void hashIncr(String key, String hashKey,long delta) {
		getRedisTemplate().opsForHash().increment(key, hashKey,delta);
	}


    /**
     * 往zset里 添加值
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, Object value,double score) {
        getRedisTemplate().opsForZSet().add(key, value, score);
    }


	/**
	 * 没有返回null , 修改成基本类型 自动拆箱会报空指针,
	 * 获取指定值的索引
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rank(String key, Object value) {
		return getRedisTemplate().opsForZSet().rank(key, value);
    }


	public void remove(String key, Object value) {
		getRedisTemplate().opsForZSet().remove(key, value);
	}

	/**
	 *	批量取无心跳用户
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<Object> removeZSet(String lockName , String key, double min, double max ) {
		Set<Object> objects = new HashSet<>();
		/**
		 * 以下不是原子操作
		 * 尝试加锁
		 * waitTime  等100ms
		 * leaseTime  锁 200ms后 锁自动释放
		 */
		try{
			boolean acquire = DistributedRedisLock.acquire(lockName, 100, 200, TimeUnit.MILLISECONDS);
			if (acquire){
				RedisTemplate<String, Object> redisTemplate = getRedisTemplate();
				objects = redisTemplate.opsForZSet().rangeByScore(key, min, max,0,1);
				if (objects.size()>0){
					Object[] array = objects.toArray();
					redisTemplate.opsForZSet().remove(key,array);
				}
			}
		}catch (Exception e ){
			logger.error("心跳退出检测 error",e);
		} finally{
			DistributedRedisLock.release(lockName);
		}

		return objects;

	}
}
