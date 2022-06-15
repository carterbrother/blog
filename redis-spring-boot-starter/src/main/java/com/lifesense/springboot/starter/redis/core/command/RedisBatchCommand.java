package com.lifesense.springboot.starter.redis.core.command;

import com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory;
import com.lifesense.springboot.starter.redis.core.utils.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.MultiKeyBinaryCommands;
import redis.clients.jedis.MultiKeyBinaryJedisClusterCommands;
import redis.clients.jedis.MultiKeyCommands;
import redis.clients.util.SafeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description 集群模式下不可使用
 *
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月9日
 * @Copyright (c) 2015, lifesense.com
 */
@Deprecated
public class RedisBatchCommand {

	protected static final Logger logger = LoggerFactory.getLogger(RedisBatchCommand.class);
	
	protected static final String RESP_OK = "OK";

	/**
	 * 指定组批量写入字符串
	 * @param groupName 缓存组
	 * @param keyValueMap 
	 * @return
	 */
	public static boolean setStringsWithGroup(String groupName,Map<String, Object> keyValueMap){
		if(keyValueMap == null || keyValueMap.isEmpty())return false;
		String[] keysValues = new String[keyValueMap.size() * 2];
		int index = 0;
		for (String key : keyValueMap.keySet()) {
			if(keyValueMap.get(key) == null)continue;
			keysValues[index++] = key;
			keysValues[index++] = keyValueMap.get(key).toString();
		}
		try {
			if(JedisProviderFactory.isCluster(groupName)){
				throw new RuntimeException("redis MultiKey don't support Cluster Mode");
			}else{
				MultiKeyCommands mkc=JedisProviderFactory.getMultiKeyCommands(groupName);
				return mkc.mset(keysValues).equals(RESP_OK);
			}
		}finally{
			JedisProviderFactory.getJedisProvider(groupName).release();
		}
	}
	
	/**
	 * 默认组批量写入字符串
	 * @param keyValueMap
	 * @return
	 */
	public static boolean setStrings(Map<String, Object> keyValueMap){
		return setStringsWithGroup(null, keyValueMap);
	}
	
	/**
	 * 指定组批量写入对象
	 * @param groupName 缓存组
	 * @param keyValueMap 
	 * @return
	 */
	public static boolean setObjectsWithGroup(String groupName,Map<String, Object> keyValueMap){
		if(keyValueMap == null || keyValueMap.isEmpty())return false;
		byte[][] keysValues = new byte[keyValueMap.size() * 2][];
		int index = 0;
		for (String key : keyValueMap.keySet()) {
			if(keyValueMap.get(key) == null)continue;
			keysValues[index++] = SafeEncoder.encode(key);
			keysValues[index++] = SerializeUtils.serialize(keyValueMap.get(key));
		}
		try{
			if(JedisProviderFactory.isCluster(groupName)){
				throw new RuntimeException("redis MultiKey don't support Cluster Mode");
			}else{
				MultiKeyBinaryCommands mbc = JedisProviderFactory.getMultiKeyBinaryCommands(groupName);
				return mbc.mset(keysValues).equals(RESP_OK);
			}
		}finally{
			JedisProviderFactory.getJedisProvider(groupName).release();
		}
	}
	
	/**
	 * 默认组批量写入对象
	 * @param keyValueMap
	 * @return
	 */
	public static boolean setObjects(Map<String, Object> keyValueMap){
		return setObjectsWithGroup(null, keyValueMap);
	}
	
	/**
	 * 按key批量从redis获取值（指定缓存组名）
	 * @param groupName
	 * @param keys
	 * @return list<String>
	 */
	public static List<String> getStringsWithGroup(String groupName,String...keys){
		try {
			if(JedisProviderFactory.isCluster(groupName)){
				throw new RuntimeException("redis MultiKey don't support Cluster Mode");
			}else{
				MultiKeyCommands mkc = JedisProviderFactory.getMultiKeyCommands(groupName);
				return mkc.mget(keys);
			}
		} finally{
			JedisProviderFactory.getJedisProvider(groupName).release();
		}
	}

	public static List<String> getStrings(String...keys){
		return getStringsWithGroup(null, keys);
	}
	
	public static boolean removeStringsWithGroup(String groupName,String...keys){
		try {
			if (JedisProviderFactory.isCluster(groupName)) {
				throw new RuntimeException("redis MultiKey don't support Cluster Mode");
			} else {
				MultiKeyCommands mkc = JedisProviderFactory.getMultiKeyCommands(groupName);
				return mkc.del(keys) == 1;
			}
		} finally{
			JedisProviderFactory.getJedisProvider(groupName).release();
		}
	}
	
    public static boolean removeStrings(String...keys){
    	return removeStringsWithGroup(null, keys);
	}
    
    
    public static boolean removeObjectsWithGroup(String groupName,String...keys){
    	byte[][] byteKeys = SafeEncoder.encodeMany(keys);
		try{
			if(JedisProviderFactory.isCluster(groupName)){
				throw new RuntimeException("redis MultiKey don't support Cluster Mode");
			}else{
				MultiKeyBinaryCommands mbc = JedisProviderFactory.getMultiKeyBinaryCommands(groupName);
				return mbc.del(byteKeys) == 1;
			}
		}finally{
			JedisProviderFactory.getJedisProvider(groupName).release();
		}
	}
	
    public static boolean removeObjects(String...keys){
    	return removeObjectsWithGroup(null, keys);
	}
	
	public static <T> List<T> getObjectsWithGroup(String groupName,String...keys){
		byte[][] byteKeys = SafeEncoder.encodeMany(keys);
		try{
			if(JedisProviderFactory.isCluster(groupName)){
				MultiKeyBinaryJedisClusterCommands mbjcc= JedisProviderFactory.getMultiKeyBinaryJedisClusterCommands(groupName);
				List<byte[]> bytes = mbjcc.mget(byteKeys);
				return listDerialize(bytes);
			}else{
				MultiKeyBinaryCommands mbc = JedisProviderFactory.getMultiKeyBinaryCommands(groupName);
				List<byte[]> bytes = mbc.mget(byteKeys);
				return listDerialize(bytes);
			}
		}finally{
			JedisProviderFactory.getJedisProvider(groupName).release();
		}
	}
	
	public static <T> List<T> getObjects(String...keys){
		return getObjectsWithGroup(null, keys);
	}

	private static <T> T valueDerialize(byte[] bytes) {
		if(bytes == null)return null;
		try {
			return (T)SerializeUtils.deserialize(bytes);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static <T> List<T> listDerialize(List<byte[]> datas){
		List<T> list = new ArrayList<>();
		if(datas == null)return list;
         for (byte[] bs : datas) {
        	 list.add(valueDerialize(bs));
		}
		return list;
	}
	
}
