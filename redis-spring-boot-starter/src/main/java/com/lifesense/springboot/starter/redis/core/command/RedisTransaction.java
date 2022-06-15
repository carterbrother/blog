package com.lifesense.springboot.starter.redis.core.command;

import com.lifesense.springboot.starter.redis.core.CacheExpires;
import com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory;
import com.lifesense.springboot.starter.redis.core.utils.RandomUtils;
import com.lifesense.springboot.starter.redis.core.utils.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.util.SafeEncoder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 集群模式下不可使用
 */
@Deprecated
public class RedisTransaction {

	private static final Logger logger = LoggerFactory.getLogger(RedisTransaction.class.getName());
	
	private String group;
	private Transaction transaction;
	
	public RedisTransaction() {
		this(null);
	}

	public RedisTransaction(String group) {
		super();
		this.group = group;
		if(JedisProviderFactory.isCluster(group)){
			throw new RuntimeException("redis Transaction don't support Cluster Mode");
		}
		Jedis jedis = (Jedis)JedisProviderFactory.getJedisProvider(group).get();
		transaction = jedis.multi();
	}
	
	public void set(String key,	Object value){
		long expire = CacheExpires.IN_1WEEK + RandomUtils.nextLong(1, CacheExpires.IN_1DAY);
		this.set(key, value, (int)expire);
	}
	
	public void set(String key,	Object value,int expireSecs){
		if(isSimpleDataType(value)){
			transaction.set(key, value.toString());
		}else{
			transaction.set(SafeEncoder.encode(key), SerializeUtils.serialize(value));
		}
		transaction.expire(key, expireSecs);
	}
	
	public void remove(String key){
		transaction.del(key);
		transaction.del(SafeEncoder.encode(key));
	}
	
	public void commit(){
		transaction.exec();
		JedisProviderFactory.getJedisProvider(group).release();
	}
	
	public void rollback(){
		transaction.discard();
		JedisProviderFactory.getJedisProvider(group).release();
	}
	
	 private static boolean isSimpleDataType(Object o) {   
		   Class<? extends Object> clazz = o.getClass();
	       return 
	       (   
	           clazz.equals(String.class) ||   
	           clazz.equals(Integer.class)||   
	           clazz.equals(Byte.class) ||   
	           clazz.equals(Long.class) ||   
	           clazz.equals(Double.class) ||   
	           clazz.equals(Float.class) ||   
	           clazz.equals(Character.class) ||   
	           clazz.equals(Short.class) ||   
	           clazz.equals(BigDecimal.class) ||     
	           clazz.equals(Boolean.class) ||   
	           clazz.isPrimitive()   
	       );   
	   }

	public static void main(String[] args) {
		System.out.println(isSimpleDataType(1));
		System.out.println(isSimpleDataType(new Date()));
	} 
}
