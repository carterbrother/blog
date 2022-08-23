package com.spring.history.demo_cache.biz;

import java.util.List;

/**
@author      relax
@since       jdk1.7,Created on 2017-4-18 上午10:49:39
@version     1.0
 **/
public abstract class MemoryAfterLoad {
	
	/**
	 * 缓存重载完毕回调
	 * @param memorys
	 */
	public abstract <T> void afterLoad(String type,List<T> memorys);
	
}
