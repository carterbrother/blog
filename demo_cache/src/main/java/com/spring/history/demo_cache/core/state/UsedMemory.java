package com.spring.history.demo_cache.core.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author  relax  
 *
 * @Email   songqinghulove@163.com
 *
 * @version Created on 2016-2-2下午4:30:22
 * 
 * @Describe
 */
public class UsedMemory {
	private static final Logger logger = LoggerFactory.getLogger(UsedMemory.class);
	private static UsedMemory instance;

	private UsedMemory() {
	}

	public static UsedMemory instance() {
		if (instance == null) {
			instance = new UsedMemory();
		}
		return instance;
	}

	public void memory() {
		int mb = 1024 * 1024;
		Runtime runtime = Runtime.getRuntime();

		logger.info("单位MB");
		logger.info("Freed Memory : " + runtime.freeMemory() / mb);
		logger.info("Used Memory: "+ (runtime.totalMemory() - runtime.freeMemory()) / mb);
		logger.info("Total Memory: " + runtime.totalMemory() / mb);
		logger.info("Max Memory:" + runtime.maxMemory() / mb);
	}

	public void nowMemory() {
		int kb = 1024;
		Runtime runtime = Runtime.getRuntime();

		logger.info("Freed Memory : " + runtime.freeMemory() / kb + " KB");
	}

	public Float calObjectSize(Object info) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream ooo;
		try {
			ooo = new ObjectOutputStream(baos);
			ooo.writeObject(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] bys = baos.toByteArray();
		// 长度减去 4 个字节：AC ED 00 05
		// AC ED -- 魔法数字
		// 00 05 -- 版本
		int max = bys.length - 4;
		Float dataLen = max / 1024F;
		ooo = null;
		baos = null;
		return dataLen;
	}

}
