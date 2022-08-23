package com.spring.history.demo_cache.biz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author      relax
@since       jdk1.7,Created on 2016-12-27 下午8:57:13
@version     1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mcache {

}
