package com.carterbrother.spriingbootpractice.chapter4_proxydemo.proxy;

import com.carterbrother.spriingbootpractice.chapter4_proxydemo.intercept.Interceptor;
import com.carterbrother.spriingbootpractice.chapter4_proxydemo.invoke.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 代理类封装
 * @date 2019年02月19日 2:07 PM
 * @Copyright (c) carterbrother
 */
public class ProxyBean implements InvocationHandler {
    private Object target = null;
    private Interceptor interceptor = null;

    public static Object getProxyBean(Object target, Interceptor interceptor) {

        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        proxyBean.interceptor = interceptor;

        Object proxy = Proxy.newProxyInstance(proxyBean.target.getClass().getClassLoader(), proxyBean.target.getClass().getInterfaces(), proxyBean);

        return proxy;

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Invocation invocation = new Invocation(args, method, target);

        this.interceptor.before();

        Object resultObject = null;

        boolean exceptionFlag = false;
        try {
            if (this.interceptor.useAround()) {
                resultObject = this.interceptor.around(invocation);
            } else {
                resultObject = method.invoke(target, args);
            }
        } catch (Exception ex) {
            exceptionFlag = true;
        }

        this.interceptor.after();
        if (!exceptionFlag) {
            this.interceptor.afterReturning();
            return resultObject;
        }
        this.interceptor.afterThrowing();
        return null;
    }


}
