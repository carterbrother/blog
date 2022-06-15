package com.springpractice.nonnull;

import lombok.NonNull;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2019年10月10日 20:16
 **/

public class Person {

    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
