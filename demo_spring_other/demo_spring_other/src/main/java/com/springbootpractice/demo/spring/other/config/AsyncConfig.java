package com.springbootpractice.demo.spring.other.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 说明：自定义异步线程池和异常处理
 * @author carter
 * 创建时间： 2020年01月13日 10:12 上午
 **/
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    /**
     * @return 线程池
     */
    @Override
    public Executor getAsyncExecutor() {


        final ThreadPoolTaskExecutor taskExecutor = new TaskExecutorBuilder()
                .corePoolSize(2)
                .maxPoolSize(4)
                .queueCapacity(10000)
                .keepAlive(Duration.ofMinutes(1))
                .threadNamePrefix("demo_spring_other_")
                .build();
        taskExecutor.initialize();

        return taskExecutor;



//        return new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(10000),
//                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("demo_spring_other_%s").build(),
//                new ThreadPoolExecutor.DiscardPolicy()
//        );
    }

    /**
     * 可以结合监控系统，监控该异常，进行告警
     * @return 异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
