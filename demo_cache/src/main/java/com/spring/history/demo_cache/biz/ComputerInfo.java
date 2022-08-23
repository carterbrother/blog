package com.spring.history.demo_cache.biz;

import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ComputerInfo {
	
	
	static String hostName;
	static String ip;
	
	/**
	 * 获取hostname
	 * @return
	 */
	public static String gethostName() {
		return System.getProperty("HOSTNAME");
	}
	
	/**
	 * 获取IP
	 * @return
	 */
	public static String getHostAddress() {
		if(!StringUtils.isEmpty(ip)) return ip;
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip = ia.getHostAddress();
	}
	
	public static void main(String[] args) {
		System.out.println(ComputerInfo.gethostName());
	}
	
		
}
