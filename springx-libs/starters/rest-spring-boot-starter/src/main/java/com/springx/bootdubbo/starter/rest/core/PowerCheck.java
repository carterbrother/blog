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
public @interface PowerCheck {
    /**
     * 权限key,建议使用appType_Service_ControllerName_method
     *
     * @return
     */
    String powerKey() default "";

    /**
     * 是否需要进一步的数据权限，
     * 如果为true，则会从账号系统中拿到该用户对应的数据权限sql;
     *
     * @return
     */
    boolean checkDataPower() default false;
}
