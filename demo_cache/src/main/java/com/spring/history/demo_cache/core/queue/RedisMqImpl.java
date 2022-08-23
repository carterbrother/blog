package com.spring.history.demo_cache.core.queue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.spring.history.demo_cache.core.MdbManager;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
@author      relax
@since       jdk1.7,Created on 2017-3-14 下午3:46:00
@version     1.0
 **/
@Component(value="listener")
public class RedisMqImpl extends RedisMsgListener{
	
	Lock lock = new ReentrantLock();
	@Override
	public void onMessage(Object message) {
		try{
			lock.lock();
			JSONObject msg = JSONObject.parseObject(message.toString());
			if(msg.containsKey("class")){
				MdbManager.getInstance().reload(msg.getString("class"));
			}
		}finally{
			lock.unlock();
		}
	}

}