package com.spring.history.demo_cache.core.state;

import com.spring.history.demo_cache.biz.LogStat;
import com.spring.history.demo_cache.core.queue.MessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;




/**
@author      relax
@since       jdk1.7,Created on 2016-10-8 上午10:17:15
@version     1.0
 **/
public class DbStatistic{
	Logger dbLog = LoggerFactory.getLogger("LogDbStat");
	private DbStatistic() {}
	
	private static class Holder {
		private static final DbStatistic instance = new DbStatistic();
	}
	
	public static DbStatistic getInstance() {
		return Holder.instance;
	}
	
	Map<Integer, Map<String,Stat>> db = new ConcurrentHashMap<Integer, Map<String,Stat>>();
	
	
	public void set(StatData data){
		if(!MessageManager.getInstance().isDbStat())return;
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int m = (int)(calendar.getTimeInMillis() / 1000 / 60);
		
		String key = data.getClassName() + "-" + data.getMethod();
		
		Map<String,Stat> stats = db.get(m);
		if(stats == null) {
			stats = new HashMap<>();
			db.put(m, stats);
		}
		Stat stat = stats.get(key);
		if(stat == null) {
			String mt = hour +""+ (minute < 10 ? "0"+minute : minute);
			stat = new Stat(data.getMethod(), data.getClassName(), data.getUseTime(), data.getUseTime(), mt);
			stat.setCount(1);
			stats.put(key, stat);
		}else {
			stat.setCount(stat.getCount() + 1);
			if(data.getUseTime() > stat.getMax()) {
				stat.setMax(data.getUseTime());
			}
			stat.setTotal(stat.getTotal() + data.getUseTime());
		}
		stat.setTimeStats(data.getUseTime());
		
		if(data.getUseTime() > 100) {
			LogStat.account(LogStat.SLOW, "db",100,data.getClassName(),data.getMethod(),"",data.getUseTime());
		}
		
	}
	
	public void log() {
		
		if(!MessageManager.getInstance().isDbStat())return;
		
		Calendar calendar = Calendar.getInstance();
		int m = (int)(calendar.getTimeInMillis() / 1000 / 60);
		Map<String,Stat> stats = db.remove(m - 1);
		if(stats != null) {
			for(Stat stat : stats.values()) {
				LogStat.account(LogStat.STAT, stat.toString(), dbLog);
			}
		}
	}
	
	public void saveLog(long start,String className,String method) {
		if(!MessageManager.getInstance().isDbStat())return;
		
		long end = System.currentTimeMillis();
		int use =(int)( end - start);
		DbStatistic.getInstance().set(new StatData(use,className,method));
	}
	

	
}
