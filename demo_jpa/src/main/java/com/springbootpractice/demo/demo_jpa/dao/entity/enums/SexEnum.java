package com.springbootpractice.demo.demo_jpa.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 3:55 下午
 **/
@AllArgsConstructor
@Getter
public enum SexEnum {

    MALE(1,"男"),
    FEMALE(1,"男");

    private Integer code;

    private String title;

    public static SexEnum getByCode(Integer code){
        return Arrays.stream(SexEnum.values()).filter(item-> Objects.equals(code,item.getCode()))
                .findFirst()
                .orElse(null);
    }

}
