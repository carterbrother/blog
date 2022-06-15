package com.lifesense.springboot.starter.redis.core.lock;


import static com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory.getJedisCommands;
import static com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory.getJedisProvider;

/**
 * 基于redis的分布式锁
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年7月21日
 * @Copyright (c) 2015, lifesense.com
 */
public class DistributeLock {

	private static final String LOCKED = "locked";
	private static final long _DEFAULT_LOCK_HOLD_MILLS = 30000;
	private String lockId;
	
	/**
	 * 
	 * @param lockId 要确保不和其他业务冲突（不能用随机生成）
	 */
	public DistributeLock(String lockId) {
		super();
		this.lockId = lockId;
	}

	/**
	 * 获取锁，默认锁定30秒
	 *@author vakinge
	 * @return
	 */
	public boolean lock(){
		return lock(_DEFAULT_LOCK_HOLD_MILLS);
	}
	
	/**
	 * 获取锁lock
	 *@author vakinge
	 * @param timeout 毫秒
	 * @return 获得lock ＝＝ true  
	 */
	public boolean lock(long timeout){
		try {		
			boolean res = getJedisCommands(null).setnx(lockId, LOCKED) > 0;
			getJedisCommands(null).pexpire(lockId, timeout);
			return res;
		} finally {
			getJedisProvider(null).release();
		}
	}
	
    public void unlock(){
    	try {			
    		getJedisCommands(null).del(lockId);
		} finally {
			getJedisProvider(null).release();
		}
	}
    
    public boolean isLocked(){
    	try {			
    		return getJedisCommands(null).exists(lockId);
		} finally {
			getJedisProvider(null).release();
		}
	
    }
}
