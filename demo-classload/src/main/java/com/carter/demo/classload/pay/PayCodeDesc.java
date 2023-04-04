package com.carter.demo.classload.pay;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PayCodeDesc {
	
	/**
	 * 支付类型【多个逗号分隔】
	 */

	String payCode();

	/**
	 * 支付子代码类型
	 */

	String subPayCode() default "";
	
	/**
	 * 支付类型描述
	 */

	String desc() default "";
	
	/**
	 * 对应路径
	 */

	String uri();

}