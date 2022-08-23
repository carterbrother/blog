package com.spring.history.demo_cache.core.state;

import java.util.concurrent.TimeUnit;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author relax
 */
public class StatHandler extends Thread {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void run() {
		Thread.currentThread().setName("StatHandler - " + getId());
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while (true) {
			try {
				
				RedisStatistic.getInstance().log();
				
				DbStatistic.getInstance().log();
				
				
				try {
					TimeUnit.SECONDS.sleep(60);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				logger.error("Exception happened", e);
			}
		}
	}

}
