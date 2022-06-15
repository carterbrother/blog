package com.carterbrother.springbootpractice.chapter3_demo.defi.impl;

import com.carterbrother.springbootpractice.chapter3_demo.defi.Animal;
import com.carterbrother.springbootpractice.chapter3_demo.defi.Person;
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
public class BussinessPerson2 implements Person {


    private Animal animal;
    public BussinessPerson2(@Autowired @Qualifier("catAnimal") Animal animal){
        setAnimal(animal);
    }

    @Override
    public void service() {
        this.animal.use();
    }


    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
