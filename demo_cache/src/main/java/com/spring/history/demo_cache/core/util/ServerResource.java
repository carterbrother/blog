package com.spring.history.demo_cache.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.IOException;
import java.util.Properties;



/**
@author      relax
@since       jdk1.7,Created on 2016-8-23 下午12:17:35
@version     1.0
 **/
public class ServerResource {
	
	public static String country;
	
	public static String resourceUrl;
	
	public static boolean debug;
	
	
	
	
	/**
	 * 需要加载的缓存类
	 */
	public static String loadClass;
	
	
	static void parse(Properties prop){
		if(prop.containsKey("country")){
			country = prop.getProperty("country");
		}
		if(prop.containsKey("resource.url")){
			resourceUrl = prop.getProperty("resource.url");
		}
		if(prop.containsKey("debug")){
			debug = Boolean.parseBoolean(prop.getProperty("debug"));
		}
		
		if(prop.containsKey("loadClass")){
			loadClass = prop.getProperty("loadClass");
		}
	}
	
	static Properties [] properties;

	/**
	 * 读取公共配置,和私有配置。
	 * 同时存在,私有配置会覆盖公共配置
	 */
	static Properties  allProp;

	public static void loadProperties(String [] props){
		
		properties = new Properties[props.length];
		
		try {
			// 资源文件解析
			int i =0;
			allProp = new Properties();
			for(String name : props){
				Properties prop = new Properties();
				prop.load(ClassLoader.getSystemResourceAsStream(name));
				allProp.load(ClassLoader.getSystemResourceAsStream(name));
				ServerResource.parse(prop);
				properties[i] = prop;
				i++;
			}
		} catch (IOException e) {
			System.exit(0);
		}
	}
	
	public static Object getProp(String key,int index){
		return properties[index].get(key);
	}
	

	public static Properties getProp(int index){
		return properties[index];
	}
	
	
	public static Object getProp(String key){
		if (allProp==null) return null;
		return allProp.get(key);
	}
	
	public static String getString(String key){
		Object item = allProp.get(key);
		if(item == null) return null;
		return item.toString();
	}
	

	public static int getIntValue(String key){
		Object item = allProp.get(key);
		if(item == null || !NumberUtils.isNumber(item.toString())) return 0;
		return Integer.parseInt(item.toString());
	}
	
}
