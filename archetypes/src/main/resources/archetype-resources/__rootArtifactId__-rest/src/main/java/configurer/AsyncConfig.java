package ${package}.configurer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author <a href="mailto:505847426@qq.com">${projectAuthor}brother</a>
 * description 异步调度器配置, 用于记录日志等要求不高的用途，进行简单的同步转异步；
 * date 2019年03月06日 4:34 PM
 * Copyright (c) ${projectAuthor}brother
 */
@EnableAsync
@Configuration
@Slf4j
public class AsyncConfig {

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(executor.getCorePoolSize() * 2);
        executor.setQueueCapacity(10000 * executor.getCorePoolSize());
        executor.setThreadNamePrefix("${rootArtifactId}".concat("Executor-"));
        executor.initialize();
        return executor;
    }

    @Bean
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> log.error("Unexpected error occurred invoking async method {} , objects: {} ,exception: {}.", method, objects, throwable);
    }

}
