package com.lifesense.springboot.starter.redis.core.command;


import com.lifesense.springboot.starter.redis.core.CacheExpires;
import com.lifesense.springboot.starter.redis.core.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合操作基类
 * @description <br>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月7日
 * @Copyright (c) 2015, lifesense.com
 */
public abstract class RedisCollection extends RedisBase {

	protected long expireTime;//过期时间（秒）

	public RedisCollection(String key) {
		this(key,DEFAULT_EXPIRE_TIME + RandomUtils.nextLong(1, 3600));
	}
	
	/**
	 * 指定组名
	 * @param key
	 * @param groupName
	 */
	public RedisCollection(String key,String groupName) {
		this(key,groupName,DEFAULT_EXPIRE_TIME + RandomUtils.nextLong(1, CacheExpires.IN_1DAY));
	}
	
	public RedisCollection(String key,long expireTime) {
		super(key);
		this.expireTime = expireTime;
	}
	
	public RedisCollection(String key,String groupName,long expireTime) {
		super(key,groupName);
		this.expireTime = expireTime;
	}
	
	protected <T> List<T> toObjectList(List<byte[]> datas) {
		List<T> result = new ArrayList<>();
    	if(datas == null)return result;
    	for (byte[] data : datas) {
			result.add(valueDerialize(data));
		}
		return result;
	}
	

}
