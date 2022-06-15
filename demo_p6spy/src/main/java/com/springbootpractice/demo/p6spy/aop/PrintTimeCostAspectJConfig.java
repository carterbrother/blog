package com.springbootpractice.demo.p6spy.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 说明：aop配置
 * @author carter
 * 创建时间： 2020年02月16日 8:49 下午
 **/
@Aspect
@Component
@Slf4j
public class PrintTimeCostAspectJConfig {

    @SneakyThrows
    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint pj) {
        Object res = null;
        String methodName = pj.getSignature().toShortString();
        StopWatch stopWatch = new StopWatch(methodName);
        stopWatch.start();
        try {
            res = pj.proceed();
        } catch (Throwable ex) {
            throw ex;
        } finally {
            stopWatch.stop();
            log.warn("{}执行耗时{}毫秒", methodName, stopWatch.getTotalTimeMillis());
        }
        return res;
    }

    @Pointcut("execution(* com.springbootpractice.demo.p6spy.web..*(..))")
    public void myPointCut() {
    }

}
