package com.carterbrother.springbootpractice.chapter3_demo.defi.impl;

import com.carterbrother.springbootpractice.chapter3_demo.defi.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月15日 3:11 PM
 * @Copyright (c) carterbrother
 */
@Component
public class DogAnimal implements Animal {

    private static final Logger logger = LoggerFactory.getLogger(DogAnimal.class);

    @Override
    public void use() {
        logger.info("狗：{} 是用来看门的", this.getClass().getSimpleName());
    }
}
