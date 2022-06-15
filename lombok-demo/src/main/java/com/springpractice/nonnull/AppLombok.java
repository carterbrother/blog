package com.springpractice.nonnull;

import lombok.NonNull;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2019年10月10日 20:20
 **/

public class AppLombok extends Something {

    private String name;

    public AppLombok(@NonNull Person person){
        super("Hello");
        this.name = person.getName();
    }

    public static void main(String[] args) {
//        new AppLombok(null);
        final Person person = new Person(null, 20);
        new AppLombok(person);
        System.out.println(person.getName());
    }


}
