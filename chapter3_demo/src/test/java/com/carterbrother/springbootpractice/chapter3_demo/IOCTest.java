package com.carterbrother.springbootpractice.chapter3_demo;

import com.carterbrother.springbootpractice.chapter3_demo.config.*;
import com.carterbrother.springbootpractice.chapter3_demo.configfile.FuckProperties;
import com.carterbrother.springbootpractice.chapter3_demo.defi.impl.BussinessPerson;
import com.carterbrother.springbootpractice.chapter3_demo.defi.impl.BussinessPerson2;
import com.carterbrother.springbootpractice.chapter3_demo.defi.impl.BussinessPerson3;
import com.carterbrother.springbootpractice.chapter3_demo.defi.impl.BussinessPerson4;
import com.carterbrother.springbootpractice.chapter3_demo.pojo.User;
import com.carterbrother.springbootpractice.chapter3_demo.pojo.User2;
import com.carterbrother.springbootpractice.chapter3_demo.scope.ScopeBean;
import com.carterbrother.springbootpractice.chapter3_demo.scope.ScopeBean2;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description
 * @date 2019年02月14日 6:14 PM
 * @Copyright (c) carterbrother
 */
public class IOCTest {

    private static final Logger logger = LoggerFactory.getLogger(IOCTest.class);

    @Test
    public void testUser() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        User user = annotationConfigApplicationContext.getBean(User.class);

        logger.info("user.id={}  ", user.getId());
    }

    @Test
    public void testUser2() {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig2.class);
        final User2 user2 = annotationConfigApplicationContext.getBean(User2.class);

        logger.info("user2:{} , id:{} , userName: {} , note: {}", user2, user2.getId(), user2.getUserName(), user2.getNote());
        logger.info("UserService is in IOC : {}", annotationConfigApplicationContext.containsBean("userService"));

    }


    @Test
    public void testUser3() {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig3.class);
        final User2 user2 = annotationConfigApplicationContext.getBean(User2.class);

        logger.info("user2:{} , id:{} , userName: {} , note: {}", user2, user2.getId(), user2.getUserName(), user2.getNote());
        logger.info("UserService is in IOC : {}", annotationConfigApplicationContext.containsBean("userService"));

    }


    @Test
    public void testAutowired() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig4.class);
        final BussinessPerson bussinessPerson = annotationConfigApplicationContext.getBean(BussinessPerson.class);
        bussinessPerson.service();

    }

    @Test
    public void testAutowired2() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig4.class);
        final BussinessPerson2 bussinessPerson = annotationConfigApplicationContext.getBean(BussinessPerson2.class);
        bussinessPerson.service();

    }

    @Test
    public void testAutowired3() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig4.class);
        final BussinessPerson3 bussinessPerson = annotationConfigApplicationContext.getBean(BussinessPerson3.class);
        bussinessPerson.service();

    }

    @Test
    public void testAutowired4() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig5.class);
        final BussinessPerson3 bussinessPerson = annotationConfigApplicationContext.getBean(BussinessPerson3.class);
        bussinessPerson.service();

    }

    @Test
    public void testAutowired5() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig5.class);
        final BussinessPerson4 bussinessPerson = annotationConfigApplicationContext.getBean(BussinessPerson4.class);
        bussinessPerson.service();
        annotationConfigApplicationContext.close();

    }


    @Test
    public void testScope() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig8.class);


        final ScopeBean bean1 = annotationConfigApplicationContext.getBean(ScopeBean.class);
        final ScopeBean bean11 = annotationConfigApplicationContext.getBean(ScopeBean.class);

        final ScopeBean2 bean21 = annotationConfigApplicationContext.getBean(ScopeBean2.class);
        final ScopeBean2 bean22 = annotationConfigApplicationContext.getBean(ScopeBean2.class);



        logger.info("bean11 : {}  ,bean12 : {}" ,bean1,bean11);
        logger.info("bean21 : {}  ,bean22 : {}" ,bean21,bean22);

        Assert.assertTrue(bean1 != bean11);
        Assert.assertTrue(bean21 == bean22);


    }






}
