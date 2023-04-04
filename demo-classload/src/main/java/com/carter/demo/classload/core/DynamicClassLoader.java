package com.carter.demo.classload.core;

public class DynamicClassLoader extends ClassLoader {
	
	/**
	 * 根据class的字节数据组装java类来实现类的动态更新
	 * @param b
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> findClass(byte[] b) throws ClassNotFoundException {
		return defineClass(null, b, 0, b.length);
	}
}