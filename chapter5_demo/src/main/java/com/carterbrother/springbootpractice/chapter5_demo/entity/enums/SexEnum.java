package com.carterbrother.springbootpractice.chapter5_demo.entity.enums;

import java.util.Arrays;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月20日 5:54 PM
 * @Copyright (c) carterbrother
 */
public enum SexEnum {
    MALE(1,"男"),FEMALE(2,"女");
    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getEnumById(int id){
        return Arrays.asList(SexEnum.values()).stream().filter(item->id == item.getId()).findFirst().orElse(null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
