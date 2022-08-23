package com.spring.history.demo_cache.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.history.demo_cache.core.RedisDateCenterCache;
import com.spring.history.demo_cache.core.util.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
@author      relax
@since       jdk1.7,Created on 2016-9-18 下午4:01:49
@version     1.0
 * @param <T>
 **/
public abstract class MemoryDb<T>{

	public List<T> memorys = new ArrayList<T>();
	
	public long reloadTime = 0;
	
	
	public MemoryDb(){
		ClassTmp.getInstance().items.add(this);
	}
	
	/**
	 * 索引-index，存储数据索引对应数据list的索引
	 */
	public Map<String, String> valueIndexs = new HashMap<String, String>();

	public Logger logger = LoggerFactory.getLogger("MdbManager");
	
	//数据初始化
	public  void dataInit(){
		this.reloadTime = System.currentTimeMillis();
		getDataFromCache();
	}
	
	@Autowired
	RedisDateCenterCache cache;
	
	public RedisDateCenterCache getCache(){
		return cache;
	}
	
	private int [] getValueIndexs(String key){
		if(!valueIndexs.containsKey(key)) return null;
		return ArrayUtils.parseIntArray(valueIndexs.get(key));
	}

	public List<T> getValueByIndexs(String key){
		int [] indexs = getValueIndexs(key);
		List<T> items = new ArrayList<T>();
		if(indexs != null && indexs.length > 0){
			for(int i:indexs){
				items.add(memorys.get(i));
			}
		}
		return items;
	}
	
	public List<T> getMemorys(){
		return memorys;
	}


	public List<JSONObject> getJSONArray(String key){
		int [] indexs = getValueIndexs(key);
		List<JSONObject> items = new ArrayList<JSONObject>();
		if(indexs != null && indexs.length > 0){
			for(int i:indexs){
				items.add((JSONObject)memorys.get(i));
			}
		}
		return items;
	}
	
	protected T getValueByIndex(String key){
		int [] indexs = getValueIndexs(key);
		if(indexs != null && indexs.length > 0){
			return memorys.get(indexs[0]);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void getDataFromCache(){
		String name = this.getClass().getSimpleName();
		if(getVersion() == 2) {
			name = getKey();
		}
		
		if(name.equals("MobileInfoMdb")){
			this.dataInit();
			return;
		}
		Map<Object, Object> items = cache.entries(name);
		memorys = (List<T>) items.get("datas");
		valueIndexs =  (Map<String, String>) items.get("indexs");
		
		if(valueIndexs == null)valueIndexs = new HashMap<String, String>();
		if(memorys == null)memorys =new ArrayList<T>();
		
	}

	public String nullToString(String value){
		return value == null ? "" : value;
	}
	
	public final String SP = "-";
	protected String compositeKey(Object...args){
		StringBuffer buff = new StringBuffer();
		int i=0;
		int len = args.length;
		for(Object item:args){
			buff.append(item == null?"":item);
			if(i != len-1)buff.append(SP);
			i++;
		}
		return buff.toString();
	}
	
	public void setCache(RedisDateCenterCache cache){
		this.cache = cache;
	}
		

	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVersion() {
		return 1;
	}

	
}
