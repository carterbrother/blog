package com.lifesense.springboot.starter.redis.core.command;

import com.lifesense.springboot.starter.redis.core.CacheExpires;
import com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory;
import com.lifesense.springboot.starter.redis.core.utils.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.util.SafeEncoder;

import java.util.Date;

import static com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory.*;


/**
 *
 * @description :redis基础操作指令 <br>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月7日
 * @Copyright (c) 2015, lifesense.com
 */
public abstract class RedisBase {

	protected static final Logger logger = LoggerFactory.getLogger(JedisProviderFactory.class.getName());

	protected static final String KEY_SUFFIX_SPLIT = "::";
//	private final static String PREFIX_KEY="redis.config.keyPrefix";
	
//	private final static Map<String,String> PREFIX_CACHE=new ConcurrentHashMap<>();
	
	/**
	 *  默认缓存时长（7 天）
	 */
	protected static final long DEFAULT_EXPIRE_TIME = CacheExpires.IN_1WEEK;
	protected static final String RESP_OK = "OK";
    protected String groupName;
    
	protected byte[] key;
	
	protected String origKey;

	public byte[] getKey() {
		return key;
	}

	public RedisBase(String key) {
		if(key.contains(KEY_SUFFIX_SPLIT)){
			this.groupName = key.split(KEY_SUFFIX_SPLIT)[0];
		}
		key=prefixKey(key,this.groupName);
		this.origKey = key;
		this.key = SafeEncoder.encode(key);
	}
	
	public RedisBase(String key,String groupName) {
		this.groupName = groupName;
		key=prefixKey(key,this.groupName);
		this.origKey = key;
		this.key = SafeEncoder.encode(key);
		
	}
	
	public static final String prefixKey(String key,String groupName){
		String appKeyPrefix = getAppKeyPrefix(groupName);
		if(com.lifesense.springboot.starter.redis.core.utils.StringUtils.isNotBlank(appKeyPrefix)){
			key=appKeyPrefix.concat(KEY_SUFFIX_SPLIT).concat(key);
		}
		return key;
	}
	
	private static String getAppKeyPrefix(String groupName){
		return JedisProviderFactory.getKeyPrefix();
//		if(StringUtils.isEmpty(groupName)){
//			groupName= RedisConstants.DEFAULT_GROUP_NAME;
//		}
//		if(PREFIX_CACHE.containsKey(groupName)){
//			return PREFIX_CACHE.get(groupName);
//		}
//		String prefixKey=groupName+"."+PREFIX_KEY;
//		String prefix = ResourceUtils.get(prefixKey,"");
//		if(StringUtils.isEmpty(prefix) && Objects.equals(groupName, RedisConstants.DEFAULT_GROUP_NAME)){
//			prefix = ResourceUtils.get(PREFIX_KEY, "");
//		}
//		PREFIX_CACHE.put(groupName, prefix);
//		return prefix;
	}

	/**
	 * 检查给定 key 是否存在。
	 * 
	 * @return
	 */
	public boolean exists() {
		try {
			if(isCluster(groupName)){
				return getBinaryJedisClusterCommands(groupName).exists(key);
			}
			return getBinaryJedisCommands(groupName).exists(key);
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
			if(isCluster(groupName)){
				return getBinaryJedisClusterCommands(groupName).del(key) == 1;
			}
			return getBinaryJedisCommands(groupName).del(key) == 1;
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
		if(seconds <= 0){
			return true;
		}
		try {
			if(isCluster(groupName)){
				return getBinaryJedisClusterCommands(groupName).pexpire(key, seconds * 1000) == 1;
			}
			return getBinaryJedisCommands(groupName).pexpire(key, seconds * 1000) == 1;
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
			if(isCluster(groupName)){
				return getBinaryJedisClusterCommands(groupName).expireAt(key, expireAt.getTime()/1000) == 1;
			}
			return getBinaryJedisCommands(groupName).expireAt(key, expireAt.getTime()/1000) == 1;
		} finally {
			getJedisProvider(groupName).release();
		}
	}
	
	public boolean setExpireIfNot(long seconds) {
		Long ttl = getTtl();
		if(ttl == -1){
			return setExpire(seconds);
		}
		return ttl >= 0;
	}

	/**
	 * 返回给定 key 的剩余生存时间
	 * 
	 * @return 当 key 不存在时，返回 -2 。
	 * 
	 *         当 key 存在但没有设置剩余生存时间时，返回 -1 。
	 * 
	 *         否则，以毫秒为单位，返回 key的剩余生存时间。
	 */
	public Long getTtl() {
		try {
			long result = 0;
			if(isCluster(groupName)){
				result = getBinaryJedisClusterCommands(groupName).ttl(key);
			}else{					
				result = getBinaryJedisCommands(groupName).ttl(key);
			}
			return result;
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
			if(isCluster(groupName)){
				return getBinaryJedisClusterCommands(groupName).persist(key) == 1;
			}
			return getBinaryJedisCommands(groupName).persist(key) == 1;
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 返回 key 所储存的值的类型。
	 * 
	 * @return none (key不存在)
	 * 
	 *         string (字符串)
	 * 
	 *         list (列表)
	 * 
	 *         set (集合)
	 * 
	 *         zset (有序集)
	 * 
	 *         hash (哈希表)
	 */
	public String type() {
		try {
			if(isCluster(groupName)){
				return getBinaryJedisClusterCommands(groupName).type(key);
			}
			return getBinaryJedisCommands(groupName).type(key);
		} finally {
			getJedisProvider(groupName).release();
		}

	}

	protected byte[] valueSerialize(Object value) {
		try {
			return SerializeUtils.serialize(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected byte[][] valuesSerialize(Object... objects) {
		try {
			byte[][] many = new byte[objects.length][];
			for (int i = 0; i < objects.length; i++) {
				many[i] = SerializeUtils.serialize(objects[i]);
			}
			return many;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T valueDerialize(byte[] bytes) {
		if(bytes == null){
			return null;
		}
		try {
			return (T)SerializeUtils.deserialize(bytes);
		} catch (Throwable e) {
			remove();
//			sendSentry(bytes);
			logger.warn(String.format("get key[%s] from redis is not null,but Deserialize error,message:%s",origKey,e.getLocalizedMessage()));
			return null;
		}
	}
	
//	protected <T> List<T> listDerialize(List<byte[]> datas){
//		List<T> list = new ArrayList<>();
//		if(datas == null){
//			return list;
//		}
//         for (byte[] bs : datas) {
//        	 list.add(valueDerialize(bs));
//		}
//		return list;
//	}

//	private void sendSentry(byte[] bytes){
//		try{
//			//sendByLog();
//			sendByOri();
//		}catch(Exception e){
//		}
//	}

//	private void sendByOri() throws Exception{
//		String dsn = ResourceUtils.get("dsn", "");
//		if(StringUtils.isNotBlank(dsn)){
//			String hostName = System.getProperty("serviceName");
//			String env = SystemUtils.getEnv();
//			if(StringUtils.isBlank(env)){
//				env = "unknown";
//			}
//			EventBuilder eventBuilder = new EventBuilder().withMessage(hostName +"_"+ env + " - RedisDerializeException - " + new String(key)).withLevel(io.sentry.event.Event.Level.ERROR).withLogger(logger.getName());
//			Sentry.getContext().addTag("category", hostName +"_"+env);
//			Sentry.getContext().addTag("hostname", InetAddress.getLocalHost().getHostName());
//			Sentry.getContext().addTag("business", "RedisDerializeException");
//			Sentry.getContext().addTag("deploy_env", env);
//			Sentry.capture(eventBuilder);
//		}
//	}
//
//	private void sendByLog() {
//		String hostName = System.getProperty("serviceName");
//		String env = SystemUtils.getEnv();
//		if(StringUtils.isBlank(env)){
//			env = "unknown";
//		}
//		com.lifesense.base.sentry.ReportEvent reportEvent = com.lifesense.base.sentry.ReportEvent.build(hostName +"_"+env,"RedisDerializeException",new String(key));
//		com.lifesense.base.sentry.LifesenseReporter.sendEvent(logger, ReportLevel.ERROR, reportEvent);
//	}
}
