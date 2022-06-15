package com.lifesense.springboot.starter.redis.core.support.local;


import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @description: 缓存本地map实现 <br>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年10月28日
 * Copyright (c) 2015, lifesense.com
 */
public class MapCacheProvider {
	
	
	private static Map<String, 	Object> cache = new ConcurrentHashMap<>();
	private static Map<String, LocalDateTime> cacheExpire = new HashMap<>();

	private static ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
			0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>(1024),
			new ThreadFactory() {
				AtomicInteger atomicInteger = new AtomicInteger(1);
				@Override
				public Thread newThread(Runnable r) {
					Thread thread = new Thread(r);
					thread.setName(String.format("%s-pool-%d",MapCacheProvider.class.getSimpleName(),atomicInteger.getAndIncrement()));
					return thread;
				}
			}
			, new ThreadPoolExecutor.AbortPolicy());


	private Lock lock = new ReentrantLock();
	private AtomicBoolean running = new AtomicBoolean();
	
	public MapCacheProvider() {
		this(1);
	}
	
	/**
	 * @param period 检查过期间隔（秒）
	 */
	public MapCacheProvider(final long period) {
		running.set(true);
		//缓存过期维护线程
		singleThreadPool.submit(() -> {
			while(running.get()){
				LocalDateTime now = LocalDateTime.now();
				lock.lock();
				try {
					Iterator<Map.Entry<String, 	LocalDateTime>> it = cacheExpire.entrySet().iterator();
					while(it.hasNext()){
						Map.Entry<String, LocalDateTime> entry=it.next();
						//过期的移除
						if(entry.getValue().compareTo(now)<=0){
							cache.remove(entry.getKey());
							it.remove();
						}
					}
					try {TimeUnit.SECONDS.sleep(period);} catch (Exception e) {}
				} finally {
					lock.unlock();
				}
			}
		});
	}

	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param timeout 单位：秒
	 * @return
	 */
	public boolean set(String key, Object value, int timeout) {
		LocalDateTime expireAt = timeout > -1 ? LocalDateTime.now().plusSeconds(timeout) : null;
		return set(key, value, expireAt);
	}

	
	public boolean set(String key, Object value, LocalDateTime expireAt) {
		cache.put(key, value);
		if(expireAt != null){
			cacheExpire.put(key, expireAt);
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T)cache.get(key);
	}

	
	public boolean remove(String key) {
		cache.remove(key);
		cacheExpire.remove(key);
		return true;
	}

	public boolean exists(String key) {
		return cache.containsKey(key);
	}
	
	public void close(){
		running.set(false);
	}

}
