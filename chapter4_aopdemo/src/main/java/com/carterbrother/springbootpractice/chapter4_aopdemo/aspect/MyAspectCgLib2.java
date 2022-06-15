package com.carterbrother.springbootpractice.chapter4_aopdemo.aspect;

import com.carterbrother.springbootpractice.chapter4_aopdemo.service.UserValidator;
import com.carterbrother.springbootpractice.chapter4_aopdemo.service.impl.UserValidatorImp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 3:33 PM
 * @Copyright (c) carterbrother
 */
@Component
@Aspect
@Order(2)
public class MyAspectCgLib2 {

    private static final Logger logger = LoggerFactory.getLogger(MyAspectCgLib2.class);

    private static final String aopFilterString = "execution(* com.carterbrother.springbootpractice.chapter4_aopdemo.service.impl.UserServiceImpl2.printUser(..))";
    private static final String pointCutString = "pointCut()";


    @DeclareParents(value = "com.carterbrother.springbootpractice.chapter4_aopdemo.service.impl.UserServiceImpl",defaultImpl = UserValidatorImp.class)
    public UserValidator userValidator;

    @Pointcut(aopFilterString)
    public void pointCut(){

    }

    @Before(pointCutString)
    public void before(){
        logger.info("===> before2 ...");
    }

    @After(pointCutString)
    public void after(JoinPoint joinPoint){
        logger.info("===> after ... , param: {} ",joinPoint.getArgs());
    }

    @AfterReturning(pointCutString)
    public void afterReturning(){

        logger.info("===> afterReturning2 ...");

    }

    @AfterThrowing(pointCutString)
    public void afterThrowing(){
        logger.info("===> afterThrowing2 ...");
    }

    @Around(pointCutString)
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("before around2 ...");
        proceedingJoinPoint.proceed();
        logger.info("after around2 ...");
    }



}
