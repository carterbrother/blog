package com.springpractice.nonnull;

import lombok.NonNull;

import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2019年10月10日 20:16
 **/

public class App  extends Something{

    private String name;

    public App(Person person){
        super("Hello");
        if (Objects.isNull(person)){
            throw new NullPointerException("person is marked @NonNull but is null");
        }
        this.name = person.getName();

    }

    public static void main(String[] args) {
        new App(null);
    }

}
