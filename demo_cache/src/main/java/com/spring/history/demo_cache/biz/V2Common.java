package com.spring.history.demo_cache.biz;

import java.io.Serializable;

/**
 * dbcenter新版
 * 
 * @author relax
 *
 */
public class V2Common extends MemoryDb<Object> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10000L;
	
	
	@Override
	public int getVersion() {
		return 2;
	}
	
	
	
	
}