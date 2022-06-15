///**
// *
// */
//package com.lifesense.springboot.starter.redis.core.pubsub;
//
//import com.lifesense.springboot.starter.redis.core.support.redis.JedisProvider;
//import com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory;
//import com.lifesense.springboot.starter.redis.core.utils.SerializeUtils;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.util.SafeEncoder;
//
///**
// * @description <br>
// * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
// * @date 2016年4月25日
// * @Copyright (c) 2015, lifesense.com
// */
//public class RedisPubClient {
//
//	public static boolean publish(String channel,Object message){
//		return publish(null, channel, message);
//	}
//
//	public static boolean publish(String group,String channel,Object message){
//		JedisProvider<?, ?> jedisProvider = JedisProviderFactory.getJedisProvider(group);
//		try {
//			if (JedisProviderFactory.isStandard(group)) {
//				Jedis jedis = (Jedis) jedisProvider.get();
//				return jedis.publish(SafeEncoder.encode(channel), SerializeUtils.serialize(message)) > 0;
//			} else if (JedisProviderFactory.isCluster(group)) {
//				JedisCluster jedis = (JedisCluster) jedisProvider.get();
//				return jedis.publish(SafeEncoder.encode(channel), SerializeUtils.serialize(message)) > 0;
//			} else {
//				throw new AssertionError();
//			}
//		} finally {
//			jedisProvider.release();
//		}
//
//	}
//}
