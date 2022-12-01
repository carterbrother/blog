package com.spring.story.demo.cache.pre;

import com.spring.story.demo.cache.pre.dao.entity.UserEntity;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/18  15:54
 **/
public class TestClassName {

    public static void main(String[] args) {

        System.out.println("UserEntity.class.getName(): "+UserEntity.class.getName());
        System.out.println("UserEntity.class.getTypeName(): "+UserEntity.class.getTypeName());

        System.out.println("UserEntity.class.getSimpleName(): "+UserEntity.class.getSimpleName());
        System.out.println("UserEntity.class.getCanonicalName(): "+UserEntity.class.getCanonicalName());


    }
}
