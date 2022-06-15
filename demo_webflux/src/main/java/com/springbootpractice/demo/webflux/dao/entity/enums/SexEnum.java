package com.springbootpractice.demo.webflux.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月15日 6:15 下午
 **/
@Getter
@AllArgsConstructor
public enum SexEnum {

    MALE(1,"男"),
    FEMALE(2,"女");

    private int code;
    private String title;


    public static SexEnum getSexEnum(int code){
       return Arrays.stream(SexEnum.values()).filter(item-> Objects.equals(code,item.getCode()))
                .findFirst()
                .orElse(null);
    }
}
