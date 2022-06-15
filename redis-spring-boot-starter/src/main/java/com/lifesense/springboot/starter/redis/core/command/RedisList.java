/**
 * 
 */
package com.lifesense.springboot.starter.redis.core.command;

import java.util.List;

import static com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory.*;


/**
 * redis操作List
 * 
 * @description <br>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月7日
 * @Copyright (c) 2015, lifesense.com
 */
public class RedisList extends RedisCollection {

	public RedisList(String key) {
		super(key);
	}
	
	/**
	 * @param key
	 * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
	 */
	public RedisList(String key,long expireTime) {
		super(key,expireTime);
	}

	/**
	 * 指定组名
	 * 
	 * @param key
	 * @param groupName
	 */
	public RedisList(String key, String groupName) {
		super(key, groupName);
	}
	
	/**
	 * 
	 * @param key
	 * @param groupName 分组名
	 * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
	 */
	public RedisList(String key,String groupName,long expireTime) {
		super(key,groupName,expireTime);
	}

	public boolean lpush(Object...objects) {
		try {
			boolean result = false;
			byte[][] datas = valuesSerialize(objects);
			if (isCluster(groupName)) {
				result = getBinaryJedisClusterCommands(groupName).lpush(key, datas) == 1;
			} else {
				result = getBinaryJedisCommands(groupName).lpush(key, datas) == 1;
			}
			//设置超时时间
			if(result)setExpireIfNot(expireTime);
			return result;
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	public boolean rpush(Object...objects) {
		try {
			byte[][] datas = valuesSerialize(objects);
			boolean result = false;
			if (isCluster(groupName)) {
				result = getBinaryJedisClusterCommands(groupName).rpush(key, datas) == 1;
			} else {
				result = getBinaryJedisCommands(groupName).rpush(key, datas) == 1;
			}
			//设置超时时间
			if(result)setExpireIfNot(expireTime);
			return result;
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	public <T> T lpop() {
		byte[] datas = null;
		try {
			if (isCluster(groupName)) {
				datas = getBinaryJedisClusterCommands(groupName).lpop(key);
			} else {
				datas = getBinaryJedisCommands(groupName).lpop(key);
			}
			return valueDerialize(datas);
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	public <T> T rpop() {
		try {

			byte[] datas = null;
			if (isCluster(groupName)) {
				datas = getBinaryJedisClusterCommands(groupName).rpop(key);
			} else {
				datas = getBinaryJedisCommands(groupName).rpop(key);
			}
			return valueDerialize(datas);
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 获取全部列表
	 * 
	 * @return
	 */
	public <T> List<T> get() {
		return range(0, -1);
	}

	public <T> List<T> range(int start, int end) {
		try {
			List<byte[]> result = null;
			if (isCluster(groupName)) {
				result = getBinaryJedisClusterCommands(groupName).lrange(key, start, end);
			} else {
				result = getBinaryJedisCommands(groupName).lrange(key, start, end);
			}
			return toObjectList(result);
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 返回长度
	 * 
	 * @return
	 */
	public long length() {
		try {
			if (isCluster(groupName)) {
				return getBinaryJedisClusterCommands(groupName).llen(key);
			} else {
				return getBinaryJedisCommands(groupName).llen(key);
			}
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 设置指定位置的值
	 * 
	 * @param index
	 * @param newValue
	 * @return
	 */
	public boolean set(long index, Object newValue) {
		try {
			boolean result = false;
			if (isCluster(groupName)) {
				result = getBinaryJedisClusterCommands(groupName).lset(key, index, valueSerialize(newValue))
						.equals(RESP_OK);
			} else {
				result = getBinaryJedisCommands(groupName).lset(key, index, valueSerialize(newValue)).equals(RESP_OK);
			}
			return result;
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 移除(所有)指定值元素
	 * @param value
	 * @return
	 */
	public boolean removeValue(Object value) {
		try {
			boolean result = false;
			if (isCluster(groupName)) {
				result = getBinaryJedisClusterCommands(groupName).lrem(key, 0, valueSerialize(value)) >= 1;
			} else {
				result = getBinaryJedisCommands(groupName).lrem(key, 0, valueSerialize(value)) >= 1;
			}
			return result;
		} finally {
			getJedisProvider(groupName).release();
		}
	}
}
