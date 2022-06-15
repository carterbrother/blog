package com.springbootpractice.aop.demoaop.aop;

import java.lang.reflect.InvocationTargetException;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 9:55 上午
 **/

public class MyInterceptor implements Iinterceptor {
    /**
     * 事前方法
     * @return
     */
    @Override
    public boolean before() {

        System.out.println("invoke before method ```");
        return true;
    }

    /**
     * 事后方法
     */
    @Override
    public void after() {
        System.out.println("invoke after method ```");

    }

    /**
     * 取代原有的方法
     * @param invocation 调用器
     * @return 返回值
     */
    @Override
    public Object around(MyInvocation invocation) throws InvocationTargetException, IllegalAccessException {

        System.out.println("invoke around method  start```");

        final Object obj = invocation.proceed();

        System.out.println("invoke around method  end```");

        return obj;
    }

    /**
     * 正常返回后执行
     */
    @Override
    public void afterReturning() {
        System.out.println("invoke afterReturning method ```");

    }

    /**
     * 异常结束后执行
     */
    @Override
    public void afterThrowning() {
        System.out.println("invoke afterThrowning method ```");

    }

    /**
     * 是否使用aroud方法
     * @return
     */
    @Override
    public boolean useAround() {
        return true;
    }
}
