package com.springbootpractice.aop.demoaop.aop.bootaop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月02日 3:10 下午
 **/
@Component
@Order(1)
@Aspect
public class MyAop {

    private static final Logger log = LogManager.getLogger(MyAop.class);

    @Pointcut("execution(public * com.springbootpractice.aop.demoaop.service..*.*(..))")
    public void pointCut(){

    }

    /**
     * 答应输入参数和响应参数
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object arount(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        log.info("params:{}",Arrays.asList(proceedingJoinPoint.getArgs()));

        Object ret = null;

        try{
            ret = proceedingJoinPoint.proceed();
        }catch (Exception e){
            log.error("exception:{}", Objects.toString(e.getMessage()),e);

            throw e;
        }finally {
            log.info("ret:{}", Objects.toString(ret));
        }

        return ret;

    }


}
