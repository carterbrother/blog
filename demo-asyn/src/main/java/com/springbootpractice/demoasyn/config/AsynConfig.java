package com.springbootpractice.demoasyn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 说明：内置异步线程池配置
 * @author  carter
 * 创建时间： 2019年07月15日 21:02
 */

@Configuration
@EnableAsync
public class AsynConfig implements AsyncConfigurer {

    public static final Logger logger = LoggerFactory.getLogger(AsynConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        final int coreSize = Runtime.getRuntime().availableProcessors();
        int maxSize = coreSize *2 +1;
        final ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
        threadPoolExecutorFactoryBean.setThreadNamePrefix("_demo_asyn");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreSize,
                maxSize,
                300,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(20000),
                threadPoolExecutorFactoryBean,
                new ThreadPoolExecutor.DiscardOldestPolicy()
                );

        return threadPoolExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                logger.error("异步任务出现异常，方法：{} , 参数：{} , 异常信息：{} ",method,objects,throwable);
            }
        };
    }
}
