package com.springx.bootdubbo.starter.rest.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 忽略登录校验的注解
 * @date 2019年05月23日 9:42 AM
 * @Copyright (c) carterbrother
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginIgnore {
}
