package com.carterbrother.springbootpractice.chapter3_demo.defi.life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 4:08 PM
 * @Copyright (c) carterbrother
 */
@Component
public class BeanPostProcessorExample implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanPostProcessorExample.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("======>invoke {} postProcessBeforeInitialization method , param: {} , {} ",this.getClass().getSimpleName(),bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("======>invoke {} postProcessAfterInitialization method , param: {} , {} ",this.getClass().getSimpleName(),bean, beanName);

        return bean;
    }
}
