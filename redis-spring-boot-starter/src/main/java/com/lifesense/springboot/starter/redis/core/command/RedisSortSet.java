/**
 * 
 */
package com.lifesense.springboot.starter.redis.core.command;

import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.lifesense.springboot.starter.redis.core.support.redis.JedisProviderFactory.*;


/**
 * redis操作可排序set
 * @description <br>
 * @author <a href="mailto:wei.jiang@lifesense.com">vakin</a>
 * @date 2015年12月7日
 * @Copyright (c) 2015, lifesense.com
 */
public class RedisSortSet extends RedisCollection {

	public RedisSortSet(String key) {
		super(key);
	}
	
	/**
	 * @param key
	 * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
	 */
	public RedisSortSet(String key,long expireTime) {
		super(key,expireTime);
	}
	
	/**
	 * 指定组名
	 * @param key
	 * @param groupName
	 */
	public RedisSortSet(String key,String groupName) {
		super(key,groupName);
	}
	
	/**
	 * 
	 * @param key
	 * @param groupName 分组名
	 * @param expireTime 超时时间(秒) 小于等于0 为永久缓存
	 */
	public RedisSortSet(String key,String groupName,long expireTime) {
		super(key,groupName,expireTime);
	}
	
	/**
	 * 新增元素
	 * @param weight 权重
	 * @param value  元素
	 * @return
	 */
	public Boolean add(double weight, Object value){
        try {
			Boolean result = false;
        	if(isCluster(groupName)){
        		result = getBinaryJedisClusterCommands(groupName).zadd(key, weight, valueSerialize(value)) >= 1;
        	}else{
        		result = getBinaryJedisCommands(groupName).zadd(key, weight, valueSerialize(value)) >= 1;
        	}
        	//设置超时时间
        	if(result)setExpireIfNot(expireTime);
			return result;
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
	
	/**
	 * 新增元素
	 * @param weight 权重
	 * @param value  元素
	 * @return
	 */
	public Double zincrby(double weight, Object value){
        try {
        	Double result = 0d;
        	if(isCluster(groupName)){
        		result = getBinaryJedisClusterCommands(groupName).zincrby(key, weight, valueSerialize(value));
        	}else{
        		result = getBinaryJedisCommands(groupName).zincrby(key, weight, valueSerialize(value));
        	}
        	//设置超时时间
        	setExpireIfNot(expireTime);
			return result;
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
	

	/**
	 * 删除有序集合中的一个成员
	 * @param member
	 * @return
	 */
	public Boolean rem(Object mem){
        try {
			Boolean result = false;
        	if(isCluster(groupName)){
        		result = getBinaryJedisClusterCommands(groupName).zrem(key,valueSerialize(mem)) >= 1;
        	}else{
        		result = getBinaryJedisCommands(groupName).zrem(key,valueSerialize(mem)) >= 1;
        	}
			return result;
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
	/**
	 * 查询集合成员数量
	 * @return
	 */
	public long count(){
        try {   
        	long count = 0;
        	if(isCluster(groupName)){
        		count = getBinaryJedisClusterCommands(groupName).zcard(key);
        	}else{
        		count = getBinaryJedisCommands(groupName).zcard(key);
        	}
			return count;
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
	
	
	/**ß
     * 获取全部列表
     * @return
     */
    public <T> List<T> get(){
    	return range(0, -1);
    }
    
    /**
     * 按指定区间取出元素列表(按权重由小至大)
     * @param start
     * @param end
     * @return
     */
    public <T> List<T> range(int start,int end){
    	Set<byte[]> result = null;
        try {    		
        	if(isCluster(groupName)){
        		result = getBinaryJedisClusterCommands(groupName).zrange(key, start, end);
        	}else{
        		result = getBinaryJedisCommands(groupName).zrange(key, start, end);
        	}
        	return toObjectList(new ArrayList<>(result));
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
    
    /**
     * 按指定区间取出元素列表(按权重由大至小)
     * @param start
     * @param end
     * @return
     */
    public <T> List<T> revRange(int start,int end){
    	Set<byte[]> result = null;
        try {    		
        	if(isCluster(groupName)){
        		result = getBinaryJedisClusterCommands(groupName).zrevrange(key, start, end);
        	}else{
        		result = getBinaryJedisCommands(groupName).zrevrange(key, start, end);
        	}
        	return toObjectList(new ArrayList<>(result));
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
    
    
	/**
	 * 按权重由小到大，获取集合成员的排名,从0开始计数
	 * @return
	 */
	public Long rank(Object mem){
        try {   
        	Long rank = 0L;
        	if(isCluster(groupName)){
        		rank = getBinaryJedisClusterCommands(groupName).zrank(key, valueSerialize(mem));
        	}else{
        		rank = getBinaryJedisCommands(groupName).zrank(key,valueSerialize(mem));
        	}
			return rank;
    	} finally{
			getJedisProvider(groupName).release();
		}
	}
	
	/**
	 * 按权重由大到小，获取集合成员的排名,从0开始计数
	 * @return
	 */
	public Long revRank(Object mem){
        try {   
        	Long rank = 0L;
        	if(isCluster(groupName)){
        		rank = getBinaryJedisClusterCommands(groupName).zrevrank(key, valueSerialize(mem));
        	}else{
        		rank = getBinaryJedisCommands(groupName).zrevrank(key,valueSerialize(mem));
        	}
			return rank;
    	} finally{
			getJedisProvider(groupName).release();
		}


	}

	/**
	 * 返回有序集 key 中，成员 member 的 score 值
	 * @return
	 */
	public Double zscore(Object mem){
		try {
			Double score;
			if(isCluster(groupName)){
				score = getBinaryJedisClusterCommands(groupName).zscore(key, valueSerialize(mem));
			}else{
				score = getBinaryJedisCommands(groupName).zscore(key,valueSerialize(mem));
			}
			return score;
		} finally{
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员
	 * 其中成员的位置按 score 值递增(从小到大)来排序
	 *
	 * @return
	 */
	public Set<Tuple> zrangeWithScores(long start, long end) {
		try {
			Set<Tuple> elements;
			if (isCluster(groupName)) {
				elements = getBinaryJedisClusterCommands(groupName).zrangeWithScores(key, start, end);
			} else {
				elements = getBinaryJedisCommands(groupName).zrangeWithScores(key, start, end);
			}
			return elements;
		} finally {
			getJedisProvider(groupName).release();
		}
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员
	 * 其中成员的位置按 score 值递减(从大到小)来排列。
	 *
	 * @return
	 */
	public Set<Tuple> zrevrangeWithScores(long start, long end) {
		try {
			Set<Tuple> elements;
			if (isCluster(groupName)) {
				elements = getBinaryJedisClusterCommands(groupName).zrevrangeWithScores(key, start, end);
			} else {
				elements = getBinaryJedisCommands(groupName).zrevrangeWithScores(key, start, end);
			}
			return elements;
		} finally {
			getJedisProvider(groupName).release();
		}
	}

}
