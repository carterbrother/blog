package com.carterbrother.springbootpractice.chapter3_demo.defi.impl;

import com.carterbrother.springbootpractice.chapter3_demo.defi.Animal;
import com.carterbrother.springbootpractice.chapter3_demo.defi.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 3:09 PM
 * @Copyright (c) carterbrother
 */
@Component
public class BussinessPerson4 implements Person , BeanNameAware , BeanFactoryAware, ApplicationContextAware , InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(BussinessPerson4.class);
    private Animal animal;

    @Override
    public void service() {
        this.animal.use();
    }


    @Override
    @Autowired @Qualifier("catAnimal")
    public void setAnimal(Animal animal) {

        logger.info("延迟依赖注入");

        this.animal = animal;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("===> invoke {} setBeanFactory method ",this.getClass().getSimpleName());
    }

    @Override
    public void setBeanName(String name) {
        logger.info("===> invoke {} setBeanName method ",this.getClass().getSimpleName());
    }

    @Override
    public void destroy() throws Exception {
        logger.info("===> invoke {} destroy method ",this.getClass().getSimpleName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("===> invoke {} afterPropertiesSet method ",this.getClass().getSimpleName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("===> invoke {} setApplicationContext method ",this.getClass().getSimpleName());
    }

    @PostConstruct
    public void selfInit(){
        logger.info("===> invoke {} selfInit method ",this.getClass().getSimpleName());

    }

    @PreDestroy
    public void selfDestroy(){
        logger.info("===> invoke {} selfDestroy method ",this.getClass().getSimpleName());

    }

}
