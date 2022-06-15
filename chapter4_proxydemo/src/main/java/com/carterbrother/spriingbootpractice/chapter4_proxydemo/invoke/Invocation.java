package com.carterbrother.spriingbootpractice.chapter4_proxydemo.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 使用发射的一个调用器
 * @date 2019年02月19日 1:49 PM
 * @Copyright (c) carterbrother
 */
public class Invocation {

    private Object[] params;

    private Method method;

    private Object target;

    public Invocation(Object[] params, Method method, Object target) {
        this.params = params;
        this.method = method;
        this.target = target;
    }


    public Object proceed() throws InvocationTargetException, IllegalAccessException{
        return  method.invoke(target,params);
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
