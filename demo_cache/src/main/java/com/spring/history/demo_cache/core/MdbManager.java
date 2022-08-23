package com.spring.history.demo_cache.core;

import java.lang.annotation.Annotation;
import java.util.*;


import com.spring.history.demo_cache.biz.*;
import com.spring.history.demo_cache.core.state.UsedMemory;
import com.spring.history.demo_cache.core.util.ArrayUtils;
import com.spring.history.demo_cache.core.util.ServerResource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
@author      relax
@since       jdk1.7,Created on 2016-9-14 下午2:41:08
@version     1.0
 **/
public class MdbManager {
	
	Logger logger = LoggerFactory.getLogger("MdbManager");
	
	public static final class Holder{
		public static final MdbManager instance = new MdbManager();
	}
	
	public static MdbManager getInstance(){
		return Holder.instance;
	}
	
	private MemoryAfterLoad afterLoad;
	
	public MemoryAfterLoad getAfterLoad() {
		return afterLoad;
	}

	public void setAfterLoad(MemoryAfterLoad afterLoad) {
		this.afterLoad = afterLoad;
	}

	private MdbManager(){
	}
	private Map<String, MemoryDb<?>> beans = new HashMap<String, MemoryDb<?>>();
	
	
	public List<MemoryDb<?>> items = new ArrayList<MemoryDb<?>>();
	
	/**
	 * 
	 * 初始化完成后回调
	 */
	public void init(){
		RedisDateCenterCache cache = null;
		//缓存类注册
		items = ClassTmp.getInstance().items;
		for(MemoryDb<?> item : items){
			
			if(cache == null)cache = item.getCache();
			
			Annotation [] anns = item.getClass().getAnnotations();
			if(anns.length > 0){
				for(Annotation ann  : anns){
					if(ann instanceof Mcache){
						String clzName = item.getClass().getSimpleName();
						if(checkLoadClass(clzName)){
							beans.put(clzName,item);
							break;
						}else{
							//logger.info("内存缓存["+clzName+"] 未配置，忽略装载！");
						}
					}
				}
			}
		}
		
		String v2dbcenter = ServerResource.getString("v2dbcenter");
		if(!StringUtils.isEmpty(v2dbcenter)) {
			String [] tables = v2dbcenter.split(",");
			for(String table : tables) {
				V2Common comm = new V2Common();
				comm.setKey(table);
				comm.setCache(cache);
				beans.put(table, comm);
			}
		}
	
		
		
		long all = 0;
		Collection<MemoryDb<?>> items = beans.values();
		for(MemoryDb<?> item : items){
			long start = System.currentTimeMillis();
			item.dataInit();
			
			String name = item.getClass().getSimpleName();
			
			if(item.getVersion() == 2) {
				name = item.getKey();
			}
			
			if(afterLoad != null){
				afterLoad.afterLoad(name,item.memorys);
			}
			
			long end = System.currentTimeMillis();
			long use = end - start;
			all+=use;
			
			float kb = UsedMemory.instance().calObjectSize(item);
			
			StringBuffer st = new StringBuffer();
			st.append(name).append("耗时：").append(use).append("ms, ");
			st.append("大小：").append(kb).append("KB, ");
			st.append("条数：").append(item.memorys.size());
			
			logger.info(st.toString());
		}
		logger.info(" ======== 初始化内存数据库完毕，总耗时："+all+"ms");
	}
	
	private boolean checkLoadClass(String clzName){
		String loadClass = ServerResource.loadClass;
		if(StringUtils.isEmpty(loadClass))return true;
		String [] classes = loadClass.split(",");
		return ArrayUtils.checkItem(classes, clzName);
	}
	
	public MemoryDb<?> getBean(String table){
		return beans.get(table);
	}
	
	
	public void reload(String name){
		if(!beans.containsKey(name)){
			return;
		}
		MemoryDb<?> item = beans.get(name);
		//防止消息多次消费
		if(item.reloadTime != 0 && System.currentTimeMillis() - item.reloadTime < 10000){
			return;
		}
		
		item.dataInit();
		
		if(afterLoad != null){
			afterLoad.afterLoad(name,item.memorys);
		}
	}
	
	
}