package com.springbootpractice.demo.mybatis.dao.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 5:56 下午
 **/
@Intercepts(@Signature(type= StatementHandler.class,method = "prepare", args = {Connection.class,Integer.class}))
public class MyPlugin implements Interceptor {

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件方法拦截");

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties =  properties;
    }
}
