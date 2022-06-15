package com.springbootpractice.aop.demoaop.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 9:48 上午
 **/

public class MyInvocation {

    private Object[] params;

    private Method method;

    private Object target;

    public MyInvocation(Object[] params, Method method, Object target) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    /**
     * 反射调用方法
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }
}
