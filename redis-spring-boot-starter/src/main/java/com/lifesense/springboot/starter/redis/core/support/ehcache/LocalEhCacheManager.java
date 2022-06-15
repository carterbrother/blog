//package com.lifesense.springboot.starter.redis.core.support.ehcache;
//
//import com.lifesense.base.utils.UUIDUtils;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;
//import net.sf.ehcache.config.CacheConfiguration;
//import net.sf.ehcache.config.MemoryUnit;
//import net.sf.ehcache.config.PersistenceConfiguration;
//import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
//import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
//import org.apache.commons.lang3.RandomStringUtils;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Date;
//
///**
// * ehcache本地缓存实现
// * @author tim
// *
// */
//public class LocalEhCacheManager {
//
//	private static CacheManager cm=CacheManager.create();
//
//	private Cache cache;
//
//	private static volatile LocalEhCacheManager singleton;
//
//	private static final int DEFAULT_MAX_CACHE_SPACE=10;
//	private static final MemoryStoreEvictionPolicy DEFAULT_MEMORY_STORE_EVICTION_POLICY=MemoryStoreEvictionPolicy.LFU;
//
//	private LocalEhCacheManager(Cache cache){
//		if(cache==null){
//			throw new NullPointerException("cache must not be null!");
//		}
//		this.cache=cache;
//		cm.addCache(cache);
//	}
//
//	/**
//	 * 创建默认缓存区域
//	 * 默认最大缓存空间10M
//	 * 回收策略是LFU
//	 *
//	 * @return
//	 */
//	public static LocalEhCacheManager singleton(){
//		if(singleton!=null){
//			return singleton;
//		}
//		synchronized (LocalEhCacheManager.class) {
//			if(singleton==null){
//				singleton=new LocalEhCacheManager(defaultCache());
//			}
//		}
//		return singleton;
//	}
//
//
//	/**
//	 * 创建一个全新的缓存区域
//	 * @param maxElements
//	 * @param maxMegabytes
//	 * @return
//	 */
//	public static LocalEhCacheManager newCache(int maxMegabytes){
//		Cache cache = new Cache(
//				 new CacheConfiguration().name(UUIDUtils.uuid())
//				 .memoryStoreEvictionPolicy(DEFAULT_MEMORY_STORE_EVICTION_POLICY)
//				 .eternal(false)
//				 .timeToLiveSeconds(30)
//				 .timeToIdleSeconds(30)
//				 .maxBytesLocalHeap(maxMegabytes, MemoryUnit.MEGABYTES)
//				 .persistence(new PersistenceConfiguration().strategy(Strategy.NONE)))
//				;
//		return new LocalEhCacheManager(cache);
//	}
//
//	private static Cache defaultCache(){
//		Cache defaultCache = new Cache(
//				 new CacheConfiguration().name("default")
//				 .memoryStoreEvictionPolicy(DEFAULT_MEMORY_STORE_EVICTION_POLICY)
//				 .eternal(false)
//				 .timeToLiveSeconds(30)
//				 .timeToIdleSeconds(30)
//				 .maxBytesLocalHeap(DEFAULT_MAX_CACHE_SPACE, MemoryUnit.MEGABYTES)
//				 .persistence(new PersistenceConfiguration().strategy(Strategy.NONE)))
//				;
//		return defaultCache;
//	}
//
//	/**
//	 * 设置缓存
//	 * @param key
//	 * @param value
//	 * @param aliveSecond 有效时间
//	 */
//	public void set(Object key,Object value,int aliveSecond){
//		checkKeyNotNull(key);
//		cache.put(new Element(key, value, false, aliveSecond, aliveSecond));
//	}
//	/**
//	 * 在key不存在时才设置
//	 * @param key
//	 * @param value
//	 * @param aliveSecond 有效时间
//	 */
//	public void setIfAbsent(Object key,Object value,int aliveSecond){
//		checkKeyNotNull(key);
//		cache.putIfAbsent(new Element(key, value, false, aliveSecond, aliveSecond));
//	}
//
//	/**
//	 * 设置缓存
//	 * @param key
//	 * @param value
//	 * @param expireTime 到期时间
//	 */
//	public void set(Object key,Object value,Date expireTime){
//		Duration duration = Duration.between(Instant.now(), Instant.ofEpochMilli(expireTime.getTime()));
//		set(key, value, Integer.valueOf(String.valueOf(duration.getSeconds())));
//	}
//	/**
//	 * 在key不存在时才设置
//	 * @param key
//	 * @param value
//	 * @param expireTime 到期时间
//	 */
//	public void setIfAbsent(Object key,Object value,Date expireTime){
//		Duration duration = Duration.between(Instant.now(), Instant.ofEpochMilli(expireTime.getTime()));
//		setIfAbsent(key, value, Integer.valueOf(String.valueOf(duration.getSeconds())));
//	}
//
//	/**
//	 * 移除key
//	 * @param key
//	 * @return
//	 */
//	public boolean remove(Object key){
//		checkKeyNotNull(key);
//		return cache.remove(key);
//	}
//
//	/**
//	 * 获取缓存
//	 * @param key
//	 * @return
//	 */
//	public Object get(Object key){
//		checkKeyNotNull(key);
//		Element element = cache.get(key);
//		if(element!=null){
//			return element.getObjectValue();
//		}
//		return null;
//	}
//
//	private void checkKeyNotNull(Object key){
//		if(key==null){
//			throw new NullPointerException("key must not be null!");
//		}
//	}
//	public Cache getCache(){
//
//		return this.cache;
//	}
//
//	public String statistics(){
//		return String.valueOf(this.cache.getStatistics());
//	}
//
//	public static void main(String[] args) {
//		long last=System.currentTimeMillis();
//		LocalEhCacheManager cache = LocalEhCacheManager.newCache(300);
//		for(int i=0;i<1000000;i++){
//			cache.set(i, RandomStringUtils.random(10), 60*60);
//		}
//		System.out.println("write use time:"+(System.currentTimeMillis()-last));
//		System.out.println(cache.getCache().calculateInMemorySize());
//		int nlc=0;
//		last=System.currentTimeMillis();
//		for(int i=0;i<1000000;i++){
//			Object object = cache.get(i);
//			if(object!=null){
//				nlc++;
//			}
//		}
//		System.out.println("read use time:"+(System.currentTimeMillis()-last));
//		System.out.println("nlc:"+nlc);
//		System.out.println(cache.getCache().calculateInMemorySize());
//	}
//
//
//}
