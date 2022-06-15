package com.springbootpractice.demo.easycode.plugin.entity;

import java.io.Serializable;

/**
 * 运营商(Carrier)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class Carrier implements Serializable {
    private static final long serialVersionUID = 264069633354882427L;
    /**
    * 编码
    */
    private String code;
    /**
    * 名称
    */
    private String name;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}