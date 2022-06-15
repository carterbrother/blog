package com.carterbrother.springbootpractice.chapter3_demo.defi.impl;

import com.carterbrother.springbootpractice.chapter3_demo.defi.Animal;
import com.carterbrother.springbootpractice.chapter3_demo.defi.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 3:09 PM
 * @Copyright (c) carterbrother
 */
@Component
public class BussinessPerson3 implements Person {

    private static final Logger logger = LoggerFactory.getLogger(BussinessPerson3.class);
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
}
