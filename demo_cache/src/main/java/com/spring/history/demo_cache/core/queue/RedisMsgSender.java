package com.spring.history.demo_cache.core.queue;

import com.alibaba.fastjson.JSONObject;
import com.spring.history.demo_cache.core.RedisDateCenterCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author relax
 * @since jdk1.7,Created on 2017-2-15 下午5:58:35
 * @version 1.0
 **/
@Component
public class RedisMsgSender {
	private static final Logger logger = LoggerFactory.getLogger("RedisMsgSender");
	
	@Autowired
	RedisDateCenterCache cache;
	
	/**
	 * 向commMsg发送消息
	 * @param msg
	 */
	public void sendCommMsg(JSONObject msg){
		cache.channelSend("commMsg", msg);
		logger.info("===== sender scuess ==== channel : commMsg , msg : "+msg);
	}
}
