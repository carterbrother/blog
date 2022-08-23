package com.spring.history.demo_cache.core;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
@author      relax
@since       jdk1.7,Created on 2017-9-11 下午2:36:20
@version     1.0
 **/
public class JedisFactory {
	Logger logger = LoggerFactory.getLogger("JedisFactory");
	private static class Holder {
		private static final JedisFactory instance = new JedisFactory();
	}

	public static JedisFactory getInstance() {
		return Holder.instance;
	}
	
	
	private final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
	
	private RedisTemplate<String, Object> commonRedisTemplate;//主redis
	
	private RedisTemplate<String, Object> bigDataRedisTemplate;//大数据redis
	
	private RedisTemplate<String, Object> slaveRedisTemplate;//备机redis
	
	private RedisTemplate<String, Object> localRedisTemplate;//本地redis
	private RedisTemplate<String, Object> newRedisTemplate;//新redis
	
	private RedisTemplate<String, Object> gameWebRedisTemplate;//game web redis
	
	private RedisTemplate<String, Object> adPutRedisTemplate;//广告投放 redis
	
	/**
	 * 公共缓存
	 * @param params
	 */
	public void redisTemplateCommon(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		//设置链接
        jedis.setPassword(params.getString("common.redis.passwd"));
        jedis.setHostName(params.getString("common.redis.host"));
        jedis.setPort(params.getIntValue("common.redis.port"));
        jedis.setUsePool(true);
        
        //设置参数
        JedisPoolConfig poolCofig =new JedisPoolConfig();
        poolCofig.setMaxIdle(100);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(200);
		poolCofig.setMaxWaitMillis(3000);
		
        jedis.setPoolConfig(poolCofig);
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
        RedisConnectionFactory connectionFactory =  factory;
        temple.setConnectionFactory(connectionFactory);
        temple.afterPropertiesSet();
        this.commonRedisTemplate = temple;
        
        logger.info(" === common redis has reload === config:"+params);
    }
	public void newTemplateCommon(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		//设置链接
		jedis.setPassword(params.getString("new.redis.passwd"));
		jedis.setHostName(params.getString("new.redis.host"));
		jedis.setPort(params.getIntValue("new.redis.port"));
		jedis.setUsePool(true);
		//"srHost":"192.168.1.231","minIdle":1,"maxIdle":100,"srPasswd":"","maxTotal":100,"crPort":"6379","timeout":10000
		//设置参数
		JedisPoolConfig poolCofig =new JedisPoolConfig();
		poolCofig.setMaxIdle(100);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(200);
		poolCofig.setMaxWaitMillis(3000);
		
		jedis.setPoolConfig(poolCofig);
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
		RedisConnectionFactory connectionFactory =  factory;
		temple.setConnectionFactory(connectionFactory);
		temple.afterPropertiesSet();
		this.newRedisTemplate = temple;
		
		logger.info(" === new redis has reload === config:"+params);
	}

	public void slaveTemplateCommon(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		//设置链接
		jedis.setPassword(params.getString("slave.redis.passwd"));
		jedis.setHostName(params.getString("slave.redis.host"));
		jedis.setPort(params.getIntValue("slave.redis.port"));
		jedis.setUsePool(true);
		//"srHost":"192.168.1.231","minIdle":1,"maxIdle":100,"srPasswd":"","maxTotal":100,"crPort":"6379","timeout":10000
		//设置参数
		JedisPoolConfig poolCofig =new JedisPoolConfig();
		poolCofig.setMaxIdle(100);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(200);
		poolCofig.setMaxWaitMillis(3000);
		
		jedis.setPoolConfig(poolCofig);
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
		RedisConnectionFactory connectionFactory =  factory;
		temple.setConnectionFactory(connectionFactory);
		temple.afterPropertiesSet();
		this.slaveRedisTemplate = temple;
		
		logger.info(" === slave redis has reload === config:"+params);
	}
	
	public void gameWebTemplateCommon(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		//设置链接
		jedis.setPassword(params.getString("gameweb.redis.passwd"));
		jedis.setHostName(params.getString("gameweb.redis.host"));
		jedis.setPort(params.getIntValue("gameweb.redis.port"));
		jedis.setUsePool(true);
		//"srHost":"192.168.1.231","minIdle":1,"maxIdle":100,"srPasswd":"","maxTotal":100,"crPort":"6379","timeout":10000
		//设置参数
		JedisPoolConfig poolCofig =new JedisPoolConfig();
		poolCofig.setMaxIdle(100);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(200);
		poolCofig.setMaxWaitMillis(3000);
		
		jedis.setPoolConfig(poolCofig);
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
		RedisConnectionFactory connectionFactory =  factory;
		temple.setConnectionFactory(connectionFactory);
		temple.afterPropertiesSet();
		this.gameWebRedisTemplate = temple;
		
		logger.info(" === gameweb redis has reload === config:"+params);
	}
	
	
	/**
	 * 大数据缓存
	 * @param params
	 */
	public void redisTemplateBigData(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		//设置链接
        jedis.setPassword(params.getString("bigdata.redis.passwd"));
        jedis.setHostName(params.getString("bigdata.redis.host"));
        jedis.setPort(params.getIntValue("bigdata.redis.port"));
        jedis.setUsePool(true);
        
        //设置参数
        JedisPoolConfig poolCofig =new JedisPoolConfig();
        poolCofig.setMaxIdle(50);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(100);
		poolCofig.setMaxWaitMillis(3000);
        
        jedis.setPoolConfig(poolCofig);
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
        RedisConnectionFactory connectionFactory =  factory;
        temple.setConnectionFactory(connectionFactory);
        temple.afterPropertiesSet();
        this.bigDataRedisTemplate = temple;
        
        logger.info(" === bigdata redis has reload === config:"+params);
    }
	public void redisTemplateAdPut(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		//设置链接
		jedis.setPassword(params.getString("adPut.redis.passwd"));
		jedis.setHostName(params.getString("adPut.redis.host"));
		jedis.setPort(params.getIntValue("adPut.redis.port"));
		jedis.setUsePool(true);
		
		//设置参数
		JedisPoolConfig poolCofig =new JedisPoolConfig();
		poolCofig.setMaxIdle(50);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(100);
		poolCofig.setMaxWaitMillis(3000);
		
		jedis.setPoolConfig(poolCofig);
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
		RedisConnectionFactory connectionFactory =  factory;
		temple.setConnectionFactory(connectionFactory);
		temple.afterPropertiesSet();
		this.adPutRedisTemplate = temple;
		
		logger.info(" === adPut redis has reload === config:"+params);
	}
	
	public void redisTemplateLocal(JSONObject params) {
		RedisTemplate<String,Object> temple = new RedisTemplate<String,Object>();
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		if(params == null) {
			params = new JSONObject();
			params.put("local.redis.host", "127.0.0.1");
			params.put("local.redis.port", 6379);
		}else {
			//设置链接
			jedis.setPassword(params.getString("local.redis.passwd"));
			jedis.setHostName(params.getString("local.redis.host"));
			jedis.setPort(params.getIntValue("local.redis.port"));
		}
		
		jedis.setUsePool(true);
		//设置参数
		JedisPoolConfig poolCofig =new JedisPoolConfig();
		poolCofig.setMaxIdle(100);
		poolCofig.setMinIdle(20);
		poolCofig.setTestOnBorrow(false);
		poolCofig.setMaxTotal(200);
		poolCofig.setMaxWaitMillis(3000);
		
		jedis.setPoolConfig(poolCofig);
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;
		
		//设置序列化模板
		temple.setKeySerializer(stringRedisSerializer);
		temple.setHashKeySerializer(stringRedisSerializer);
		temple.setHashValueSerializer(stringRedisSerializer);
		temple.setValueSerializer(stringRedisSerializer);
		RedisConnectionFactory connectionFactory =  factory;
		temple.setConnectionFactory(connectionFactory);
		temple.afterPropertiesSet();
		this.localRedisTemplate = temple;
		
		logger.info(" === local redis has reload === config:"+params);
	}
	
	
	
	
	public RedisTemplate<String, Object> getCommon(){
		return commonRedisTemplate;
	}
	
	public RedisTemplate<String, Object> getBigData(){
		return bigDataRedisTemplate;
	}
	
	public RedisTemplate<String, Object> getSlave(){
		return slaveRedisTemplate;
	}
	
	public RedisTemplate<String, Object> getLocal(){
		return localRedisTemplate;
	}
	
	public RedisTemplate<String, Object> getNew(){
		return newRedisTemplate;
	}
	
	public RedisTemplate<String, Object> getGameWeb(){
		return gameWebRedisTemplate;
	}
	public RedisTemplate<String, Object> getAdPut(){
		return adPutRedisTemplate;
	}
	
	
	public static void main(String[] args) {
		
		JSONObject params = new JSONObject();
		params.put("common.redis.host", "192.168.1.231");
		params.put("common.redis.passwd", "");
		params.put("common.redis.port", 6379);
		params.put("maxIdle", 100);
		params.put("minIdle", 1);
		
		JedisFactory factory = JedisFactory.getInstance();
		RedisCache cache = new RedisCache();
		
		//初始化模板
		factory.redisTemplateCommon(params);
		
//		cache.setRedisTemplate(factory.getCommon());
		
//		string 测试
//		cache.set("test_110", "哈哈哈哈");
//		System.out.println(cache.get("test_110"));
//		cache.delete("test_110");
//		System.out.println(cache.get("test_110"));
		
		//原子追加
		cache.set("money_110", 1000);
		System.out.println(cache.get("money_110", 0));
		cache.increment("money_110", 1000);
		System.out.println("原子追加:"+cache.get("money_110", 0));
//		cache.delete("money_110");
		
	}
	
}
