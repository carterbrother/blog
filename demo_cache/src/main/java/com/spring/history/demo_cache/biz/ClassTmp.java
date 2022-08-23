package com.spring.history.demo_cache.biz;

import java.util.ArrayList;
import java.util.List;


/**
@author      relax
@since       jdk1.7,Created on 2017-7-17 下午7:29:10
@version     1.0 中转类，解决加载顺序问题
 **/
public class ClassTmp {
	public static final class Holder{
		public static final ClassTmp instance = new ClassTmp();
	}
	
	public static ClassTmp getInstance(){
		return Holder.instance;
	}
	
	public List<MemoryDb<?>> items = new ArrayList<MemoryDb<?>>();
}
