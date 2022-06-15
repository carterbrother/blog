package com.lifesense.springboot.starter.redis.core.command;


import com.lifesense.springboot.starter.redis.core.CacheExpires;
import com.lifesense.springboot.starter.redis.core.utils.RandomUtils;

import java.util.Date;

import static com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory.*;


/**
 * 字符串redis操作命令
 * 
 * @description <br>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月7日
 * @Copyright (c) 2015, lifesense.com
 */
public class RedisString {
	
	/**
	 *  默认缓存时长（7 天）
	 */
	protected static final int DEFAULT_EXPIRE_TIME = 60 * 60 * 24 * 7;
	
	protected static final String RESP_OK = "OK";

	protected String key;
	
	protected String groupName;

	public RedisString(String key) {
		if(key.contains(RedisBase.KEY_SUFFIX_SPLIT)){
			this.groupName = key.split(RedisBase.KEY_SUFFIX_SPLIT)[0];
		}
		this.key = RedisBase.prefixKey(key, this.groupName);
	}
	
	/**
	 * 
	 * @param key
	 * @param groupName 组名
	 */
	public RedisString(String key,String groupName) {
		this.groupName = groupName;
		this.key = RedisBase.prefixKey(key, this.groupName);
	}

	/**
	 * 重置key（适合一个方法里面频繁操作不同缓存的场景）<br>
	 * <font color="red">非线程安全，请不要在多线程场景使用</font>
	 * 
	 * @param key
	 * @return
	 */
	//TODO:key 改造
	public RedisString resetKey(String key) {
		this.key = RedisBase.prefixKey(key, this.groupName);
		return this;
	}
	
	/**
	 * 设置缓存，默认过期时间(DEFAULT_EXPIRE_TIME)
	 * @param value
	 * @return
	 */
	public boolean set(String value){
		//避免某些缓存同时失效，加随机时长
		long expire = DEFAULT_EXPIRE_TIME + RandomUtils.nextLong(1, CacheExpires.IN_1DAY);
		return set(value, expire);
	}

    /**
     * 设置缓存指定过期时间间隔
     *
     * @param value
     * @param seconds (过期秒数 ，小于等于0时 不设置)
     * @return
     */
    public boolean set(String value, long seconds) {

        if (value == null)
            return false;
        try {
            boolean result = false;
            if (isCluster(groupName)) {
                result = getJedisClusterCommands(groupName).set(key, value).equals(RESP_OK);
            } else {
                result = getJedisCommands(groupName).set(key, value).equals(RESP_OK);
            }
            if (result && seconds > 0) {
                result = setExpire(seconds);
            }
            return result;
        } finally {
            getJedisProvider(groupName).release();
        }

    }


	public boolean set(String value, Date expireAt) {
		if (value == null)
			return false;
		try {
            boolean result = false;
            if (isCluster(groupName)) {
                result = getJedisClusterCommands(groupName).set(key, value).equals(RESP_OK);
            } else {
                result = getJedisCommands(groupName).set(key, value).equals(RESP_OK);
            }
			if(result){
				result = setExpireAt(expireAt);
			}
			return result;
		} finally {
			getJedisProvider(groupName).release();
		}
	}
	
	public String get() {
		try {
            String value;
            if (isCluster(groupName)) {
                value = getJedisClusterCommands(groupName).get(key);;
            } else {
                value = getJedisCommands(groupName).get(key);
            }
			return value;
		} finally {
			getJedisProvider(groupName).release();
		}
		
	}


    /**
     * 检查给定 key 是否存在。
     *
     * @return
     */
    public boolean exists() {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).exists(key);
            } else {
                return getJedisCommands(groupName).exists(key);
            }
        } finally {
            getJedisProvider(groupName).release();
        }

    }

	/**
	 * 删除给定的一个 key 。
	 * 
	 * 不存在的 key 会被忽略。
	 * 
	 * @return true：存在该key删除时返回
	 * 
	 *         false：不存在该key
	 */
    public boolean remove() {
        try {
            if (isCluster(groupName)) {
                return getJedisClusterCommands(groupName).del(key) == 1;
            } else {
                return getJedisCommands(groupName).del(key) == 1;
            }
        } finally {
            getJedisProvider(groupName).release();
        }
    }

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * 
	 * @param seconds
	 *            超时时间，单位：秒
	 * @return true：超时设置成功
	 * 
	 *         false：key不存在或超时未设置成功
	 */
	public boolean setExpire(long seconds) {
		try {
			if (isCluster(groupName)) {
				return getJedisClusterCommands(groupName).pexpire(key, seconds * 1000) == 1;
			} else {
				return getJedisCommands(groupName).pexpire(key, seconds * 1000) == 1;
			}

		} finally {
			getJedisProvider(groupName).release();
		}

	}

	/**
	 * 
	 * 设置指定时间戳时失效
	 *
	 * 注意：redis服务器时间问题
	 * 
	 * @param expireAt
	 *            超时时间点
	 * @return true：超时设置成功
	 *
	 *         false：key不存在或超时未设置成功
	 */
	public boolean setExpireAt(Date expireAt) {
		if(expireAt == null){
			return false;
		}
		try {
			if (isCluster(groupName)) {
				return getJedisClusterCommands(groupName).expireAt(key, expireAt.getTime()/1000) == 1;
			} else {
				return getJedisCommands(groupName).expireAt(key, expireAt.getTime()/1000) == 1;
			}
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 返回给定 key 的剩余生存时间(单位：秒)
	 * 
	 * @return 当 key 不存在时，返回 -2 。
	 * 
	 *         当 key 存在但没有设置剩余生存时间时，返回 -1 。
	 * 
	 *         否则，以毫秒为单位，返回 key的剩余生存时间。
	 */
	public Long getTtl() {
		try {

			if (isCluster(groupName)) {
				return getJedisClusterCommands(groupName).ttl(key);
			} else {
				return getJedisCommands(groupName).ttl(key);
			}
		} finally {
			getJedisProvider(groupName).release();
		}

	}

	/**
	 * 移除给定 key 的生存时间，设置为永久有效
	 * 
	 * @return 当生存时间移除成功时，返回 1 .
	 * 
	 *         如果 key 不存在或 key 没有设置生存时间，返回 0 。
	 */
	public boolean removeExpire() {
		try {
			if (isCluster(groupName)) {
				return getJedisClusterCommands(groupName).persist(key) == 1;
			} else {
				return getJedisCommands(groupName).persist(key) == 1;
			}
		} finally {
			getJedisProvider(groupName).release();
		}
	}
}
