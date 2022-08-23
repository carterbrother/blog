package com.spring.history.demo_cache.core.queue;

import java.util.concurrent.*;


import com.spring.history.demo_cache.biz.LogStat;
import com.spring.history.demo_cache.core.state.StatHandler;
import com.spring.history.demo_cache.core.util.SysInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author relax
 * @since jdk1.7,Created on 2016-9-20 下午6:12:07
 * @version 1.0
 **/
public class MessageManager {

	private final Logger logger = LoggerFactory.getLogger("MessageManager");

	private MessageManager() {
	}

	private static class Holder {
		private static final MessageManager instance = new MessageManager();
	}

	public static MessageManager getInstance() {
		return Holder.instance;
	}

	private boolean redisStat;
	private boolean httpStat;
	private boolean dbStat;

	public boolean isRedisStat() {
		return redisStat;
	}

	public void setRedisStat(boolean redisStat) {
		this.redisStat = redisStat;
	}

	public boolean isHttpStat() {
		return httpStat;
	}

	public void setHttpStat(boolean httpStat) {
		this.httpStat = httpStat;
	}

	public boolean isDbStat() {
		return dbStat;
	}

	public void setDbStat(boolean dbStat) {
		this.dbStat = dbStat;
	}
	
    // 定时任务执行器
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, r -> {
		Thread thread = new Thread();
		thread.setName("SysInfoTimer");
		thread.setDaemon(true);
		return thread;
	});

	/**
	 * 
	 * @param redisCache redis 统计 true 开启统计
	 * @param http http 统计 true 开启统计
	 * @param db   db 统计 true 开启统计
	 */
	public void initStat(boolean redisCache, boolean http, boolean db) {
		setDbStat(db);
		setHttpStat(http);
		setRedisStat(redisCache);
		
		if(db || redisCache) {
			// 数据搜集线程
			ExecutorService stat = Executors.newCachedThreadPool();
			for (int i = 0; i < 1; i++) {
				stat.execute(new StatHandler());
			}
			logger.info("统计线程启动");
		}
		
		// 启动统计信息收集定时器
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                // 收集统计信息
                try {
                	LogStat.account(LogStat.SYSTEM, SysInfo.sysInfo());
                } catch (Throwable t) { // 防御性容错
                    logger.error("sys info log error, cause: " + t.getMessage(), t);
                }
            }
        }, 20000, 20000, TimeUnit.MILLISECONDS);
		
	}

}
