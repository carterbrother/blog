package com.springx.bootdubbo.starter.cache.core.support.local;//package com.lifesense.springboot.starter.redis.core.support.local;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisPubSub;
//
//import java.util.*;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 本地缓存同步处理器
// * @description <br>
// * @author <a href="mailto:505847426@qq.com">carterbrother</a>
// * @date 2016年6月21日
// * @Copyright (c) 2015, springx.com
// */
//public class LocalCacheSyncProcessor implements DisposableBean {
//
//	private static final Logger logger = LoggerFactory.getLogger(LocalCacheSyncProcessor.class);
//
//	private static String channelName = "clearLevel1Cache";
//
//	private String servers;
//    private boolean enable = false;
//
//	private long maxSize = 100000;
//	private long expireAfterMins = 60 * 24;
//
//	private List<String> keyPrefixs;
//	private Jedis subJedisClient;
//	private JedisPool pupJedisPool;
//
//	private ScheduledExecutorService executorService;
//
//	private LocalCacheSyncListener listener;
//
//	public   LocalCacheSyncProcessor(){
//		if(!enable)return;
//		Objects.requireNonNull(keyPrefixs);
//		assert StringUtils.hasLength(servers);
//
//		String[] serverInfos = StringUtils.tokenizeToStringArray(servers, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS)[0].split(":");
//
//		String host =serverInfos[0];
//		int port = Integer.parseInt(serverInfos[1]);
//
//		listener = new LocalCacheSyncListener();
//
//
//		 executorService = new ScheduledThreadPoolExecutor(1,
//				new ThreadFactory() {
//				AtomicInteger atomicInteger = new AtomicInteger(1);
//					@Override
//					public Thread newThread(Runnable r) {
//						Thread thread = new Thread(r);
//						thread.setName(String.format("LocalCacheSyncProcessor-schedule-pool-%d",atomicInteger.getAndIncrement()));
//						thread.setDaemon(true);
//						return thread;
//					}
//				});
//		executorService.scheduleAtFixedRate(() -> {
//			//do something
//			if(subJedisClient == null){
//				try {
//					subJedisClient = new Jedis(host, port);
//					enable = true;
//					if("PONG".equals(subJedisClient.ping())){
//						logger.info("subscribe localCache sync channel.....");
//						subJedisClient.subscribe(listener, new String[]{channelName});
//					}
//				} catch (Exception e) {
//					enable = false;
//					try {listener.unsubscribe();} catch (Exception ex) {}
//					try {
//						subJedisClient.close();
//					} catch (Exception e2) {}finally {
//						subJedisClient = null;
//					}
//				}
//			}
//		},1,30, TimeUnit.SECONDS);
//
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxIdle(1);
//		poolConfig.setMinEvictableIdleTimeMillis(60 * 1000);
//		poolConfig.setMaxTotal(50);
//		poolConfig.setMaxWaitMillis(30 * 1000);
//		pupJedisPool = new JedisPool(poolConfig, host, port, 3000);
//
//		LocalCacheProvider.init(keyPrefixs, maxSize,expireAfterMins);
//	}
//
//	public boolean publishSyncEvent(String key){
//		if(enable ==false){
//			return true;
//		}
//		for (String prefix : keyPrefixs) {
//			if(key.indexOf(prefix) == 0){
//				logger.debug("redis publish:{} for key:{}",channelName,key);
//				return publish(channelName, key);
//			}
//		}
//		return true;
//	}
//
//	private boolean publish(String channel,String message){
//		Jedis jedis = null;
//		try {
//			jedis = pupJedisPool.getResource();
//			return jedis.publish(channel, message) > 0;
//		} finally {
//			if(jedis != null){
//				jedis.close();
//			}
//		}
//
//	}
//
//
//	@Override
//	public void destroy() throws Exception {
//		executorService.shutdown();
//		try {listener.unsubscribe();} catch (Exception e) {}
//		if(subJedisClient != null){
//			subJedisClient.close();
//		}
//		if(pupJedisPool != null){
//			pupJedisPool.close();
//		}
//
//	}
//
//	public void setServers(String servers) {
//		this.servers = servers;
//	}
//
//	public void setEnable(boolean enable) {
//		this.enable = enable;
//	}
//
//	public void setExpireAfterMins(long expireAfterMins) {
//		this.expireAfterMins = expireAfterMins;
//	}
//
//	public void setMaxSize(long maxSize) {
//		this.maxSize = maxSize;
//	}
//
//	public void setKeyPrefixs(String keyPrefixs) {
//		if(com.lifesense.springboot.starter.redis.core.utils.StringUtils.isBlank(keyPrefixs)){
//			return;
//		}
//		String[] tmpKeyPrefixs = StringUtils.tokenizeToStringArray(keyPrefixs, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
//		this.keyPrefixs = new ArrayList<>(Arrays.asList(tmpKeyPrefixs));
//	}
//
//
//
//	private class LocalCacheSyncListener extends JedisPubSub {
//
//		private static final String CLEAR_ALL = "clearall";
//
//		@Override
//		public void onMessage(String channel, String message) {
//			super.onMessage(channel, message);
//			if(channel.equals(channelName)){
//				if(CLEAR_ALL.equals(message)){
//					LocalCacheProvider.getInstance().clearAll();
//					logger.info("receive command {} and clear local cache finish!",CLEAR_ALL);
//				}else{
//					LocalCacheProvider.getInstance().remove(message);
//				}
//			}
//		}
//	}
//
//}
