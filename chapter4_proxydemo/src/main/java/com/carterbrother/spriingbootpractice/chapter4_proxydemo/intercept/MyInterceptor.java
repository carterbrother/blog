package com.carterbrother.spriingbootpractice.chapter4_proxydemo.intercept;

import com.carterbrother.spriingbootpractice.chapter4_proxydemo.invoke.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 拦截器
 * @date 2019年02月19日 1:52 PM
 * @Copyright (c) carterbrother
 */
public class MyInterceptor implements Interceptor{

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    /**
     * 事前方法
     *
     * @return
     */
    @Override
    public boolean before() {
        logger.info("===>before ......");
        return true;
    }

    /**
     * 事后方法
     */
    @Override
    public void after() {
        logger.info("======> after ......");
    }

    /**
     * 取代原有是事件方法
     *
     * @param invocation 回调参数
     * @return 原有事件返回对象
     * @throws InvocationTargetException
     * @throws IllegalAccessError
     */
    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {

        logger.info("===> around before ......");

        Object object = invocation.proceed();

        logger.info("===> around after ......");


        return object;
    }

    /**
     * 事后返回方法，事件没有发生异常执行
     */
    @Override
    public void afterReturning() {
        logger.info("======> afterReturning ......");
    }

    /**
     * 事后异常方法，事件发生异常之后执行
     */
    @Override
    public void afterThrowing() {
        logger.info("======> afterThrowing ......");
    }

    /**
     * 是否使用around方法替代原有方法
     *
     * @return
     */
    @Override
    public boolean useAround() {
        return true;
    }
}
