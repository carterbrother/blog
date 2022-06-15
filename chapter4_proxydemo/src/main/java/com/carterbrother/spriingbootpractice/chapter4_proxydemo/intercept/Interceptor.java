package com.carterbrother.spriingbootpractice.chapter4_proxydemo.intercept;



import com.carterbrother.spriingbootpractice.chapter4_proxydemo.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 拦截器接口
 * @date 2019年02月19日 1:45 PM
 * @Copyright (c) carterbrother
 */
public interface Interceptor {

    /**
     * 事前方法
     * @return
     */
    boolean before();

    /**
     * 事后方法
     */
    void after();

    /**
     * 取代原有是事件方法
     * @param invocation 回调参数
     * @return 原有事件返回对象
     * @throws InvocationTargetException
     * @throws IllegalAccessError
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 事后返回方法，事件没有发生异常执行
     */
    void afterReturning();

    /**
     * 事后异常方法，事件发生异常之后执行
     */
    void afterThrowing();

    /**
     * 是否使用around方法替代原有方法
     * @return
     */
    boolean useAround();
}
