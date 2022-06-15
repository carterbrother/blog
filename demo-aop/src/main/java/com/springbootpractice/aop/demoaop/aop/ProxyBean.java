package com.springbootpractice.aop.demoaop.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 9:58 上午
 **/

public class ProxyBean implements InvocationHandler {

    private Object target;

    private Iinterceptor interceptor;

    /**
     * target必须存在接口 ，可以返回接口的实现类
     * @param target
     * @param interceptor
     * @return
     */
    public static Object getProxyBean(Object target, Iinterceptor interceptor){

        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        proxyBean.interceptor = interceptor;

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),proxyBean);

        return proxy;

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean exceptionFlag = false;

        MyInvocation myInvocation = new MyInvocation(args,method,target);

        Object res = null;

        try{
        if (this.interceptor.before()){
            res = interceptor.around(myInvocation);
        }else{
            method.invoke(target,args);
        }}
        catch (Exception e){
            exceptionFlag = true;
        }

        this.interceptor.after();

        if (exceptionFlag){
            this.interceptor.afterThrowning();
        }else{
            this.interceptor.afterReturning();
        }

        return res;
    }
}
