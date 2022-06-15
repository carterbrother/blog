package com.lifesense.springboot.starter.redis.core.support.redis;

import org.springframework.beans.factory.DisposableBean;

/**
 * @author lifesense-szyf01
 * @description 核心操作接口
 * @date 2015年12月8日
 * @Copyright (c) 2015, lifesense.com
 */
public interface JedisProvider<S,B> extends DisposableBean {
	/**
	 * 获取连接
	 * @return
	 */
	S get();

	/**
	 * 获取二进制连接
	 * @return
	 */
	B getBinary();

	/**
	 * 释放连接
	 */
	void release();

	/**
	 * redis的使用模式
	 * @return
	 */
	String mode();

	/**
	 * redis群组
	 * @return
	 */
	String groupName();

}
