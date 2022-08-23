package com.spring.history.demo_cache.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
@author      relax
@since       jdk1.7,Created on 2016-10-11 下午2:38:34
@version     1.0
 **/
public class MatcherUtils {
	
	/** 匹配以mz.mzddz.、365ios.zyqp.开头的字符串 */
	public static final String P_1="^mz\\.mzddz\\..*|^365ios\\.zyqp\\..*";
	
	/** 匹配以mz、meizu、huwei开头的字符串 */
	public static final String P_2="^mz\\..*|meizu\\..*|huawei\\..*";
	
	/** 匹配以jinli、amigo、365you.nttddzzrb.2200126429开头的字符串 */
	public static final String P_3="^jinli.*|amigo.*|365you\\.nttddzzrb\\.2200126429.*";
	
	/** 匹配常用电话号码 */
	public static final String PHONE="^1[345789]\\d{9}$";
	
	/** 以2200123366或2200169810结尾 */
	public static final String BBG="^.*.(2200123366|2200169810)";
	
	
	
	/**
	 * 正则验证, 全匹配
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static boolean matchAll(String source,String pattern){
		if(StringUtils.isEmpty(source)) return false;
		
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(source);
		return m.matches();
	}
	
	/**
	 * 找到有就ok, 非全匹配
	 * @param source  匹配数据
	 * @param pattern 匹配表达式
	 * @return
	 */
	public static boolean verify(String source,String pattern){
		pattern = pattern == null ? "" : pattern;
		if(StringUtils.isEmpty(source)) return false;
		
		
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(source);
		return (m.find());
	}
	
	public static void main(String[] args) {
		//System.out.println(verify("gr13ios.hlddz.2200126429.112", "^((?!gr12ios.hlddz|gr13ios.hlddz|gr17ios.hlddz|groios.ddzdjb|gr3ios.hlddz|360.glddzdjs.2200196424).)"));

		System.out.println(verify("apple","vivo.hldjddz.2200169810|dianle|ios|leba.fkttddz.2200169810|vivo.ddzdj.2200169810"));


//		System.out.println(verify("18370209749", PHONE));
		
		
	}
}
