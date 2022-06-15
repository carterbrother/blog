package com.springbootpractice.aop.demoaop.aop;

import java.lang.reflect.InvocationTargetException;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 9:44 上午
 **/

public interface Iinterceptor {
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
     * 取代原有的方法
     * @param invocation 调用器
     * @return 返回值
     */
    Object around(MyInvocation invocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 正常返回后执行
     */
    void afterReturning();

    /**
     * 异常结束后执行
     */
    void afterThrowning();

    /**
     * 是否使用aroud方法
     * @return
     */
    boolean useAround();

}
