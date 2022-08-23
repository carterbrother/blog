package com.spring.history.demo_cache.core.state;

import com.alibaba.fastjson.JSONObject;

public class Stat {

	private String method;

	private String className;

	private int total;

	private int max;

	private String minute;

	private int count;

	private JSONObject timeStat = new JSONObject();

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public Stat(String method, String className, int total, int max, String minute) {
		super();
		this.method = method;
		this.className = className;
		this.total = total;
		this.max = max;
		this.minute = minute;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public void setTimeStats(int useTime) {
		String key = "0-50";
		if(useTime < 50) {
			key = "0-50";
		}
		else if(useTime >= 50 && useTime < 100) {
			key = "50-100";
		}
		else if(useTime >= 100 && useTime < 500) {
			key = "100-500";
		}
		else{
			key = "T500";
		}
		timeStat.put(key, timeStat.getIntValue(key)+1);
	}
	
	public String getTimeStats() {
		return timeStat.getIntValue("0-50")+"|"+timeStat.getIntValue("50-100")+"|"+timeStat.getIntValue("100-500")+"|"+timeStat.getIntValue("T500");
	}
	
	

	@Override
	public String toString() {
		return className + "|" + method + "|" + count + "|" + max + "|" + total+ "|" +getTimeStats();

	}

}
