///**
// *
// */
//package com.lifesense.springboot.starter.redis.core.pubsub;
//
//import com.lifesense.springboot.starter.redis.core.utils.SerializeUtils;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.BinaryJedisPubSub;
//import redis.clients.jedis.Jedis;
//import redis.clients.util.SafeEncoder;
//
///**
// * @description <br>
// * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
// * @date 2016年4月25日
// * @Copyright (c) 2015, lifesense.com
// */
//public class RedisSubscriber implements InitializingBean, DisposableBean {
//
//	private String servers;
//	private String channels;
//	private SubscriberMessageHandler listener;
//
//	private Jedis client;
//
//	public void setServers(String servers) {
//		this.servers = servers;
//	}
//
//	public void setChannels(String channels) {
//		this.channels = channels;
//	}
//
//	public void setListener(SubscriberMessageHandler listener) {
//		this.listener = listener;
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//
//		String[] channels = StringUtils.tokenizeToStringArray(this.channels, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
//		Assert.noNullElements(channels,"channel中不能含有null元素");
//
//		String[] parts = servers.split(",")[0].split("\\:");
//
//		client = new Jedis(parts[0], Integer.parseInt(parts[1]));
//		client.subscribe(new SubscriberListener(), SafeEncoder.encodeMany(channels));
//	}
//
//
//
//	@Override
//	public void destroy() throws Exception {
//		client.close();
//	}
//
//
//
//	private class SubscriberListener extends BinaryJedisPubSub{
//
//		@Override
//		public void onMessage(byte[] channel, byte[] message) {
//			listener.onMessage(SafeEncoder.encode(channel), SerializeUtils.deserialize(message));
//		}
//
//	}
//
//}
