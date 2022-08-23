package com.spring.history.demo_cache.core.state;

public class StatData {

	private int useTime;

	private String className;

	private String method;

	public int getUseTime() {
		return useTime;
	}

	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public StatData(int useTime, String className, String method) {
		super();
		this.useTime = useTime;
		this.className = className;
		this.method = method;
	}

}
