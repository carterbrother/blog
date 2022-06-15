package com.springbootpractice.demo.demo_jpa.dao.converter;

import com.springbootpractice.demo.demo_jpa.dao.entity.enums.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 3:56 下午
 **/

public class SexConverter implements AttributeConverter<SexEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getCode();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getByCode(integer);
    }
}
