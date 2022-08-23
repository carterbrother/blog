package com.spring.history.demo_cache.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * @author relax
 * @since jdk1.7,Created on 2017-1-5 下午3:17:00
 * @version 1.0
 **/
public class URLOperate {
	
	
	public static String decode(String data) {
		data = data == null ? "" : data;
		String v = "";
		try {
			v = URLDecoder.decode(data, Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return v;
	}
	

	public static String encode(String data) {
		data = data == null ? "" : data;
		String v = "";
		try {
			v = URLEncoder.encode(data, Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return v;
	}

}
