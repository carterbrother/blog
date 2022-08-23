package com.spring.history.demo_cache.core.queue;



/**
 * @author relax
 * @since jdk1.7,Created on 2017-2-15 下午5:58:35
 * @version 1.0
 **/
public abstract class RedisMsgListener {
	abstract public void onMessage(Object message);
}
