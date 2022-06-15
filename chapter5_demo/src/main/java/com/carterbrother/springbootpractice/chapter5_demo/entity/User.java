package com.carterbrother.springbootpractice.chapter5_demo.entity;

import com.carterbrother.springbootpractice.chapter5_demo.entity.enums.SexEnum;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月20日 5:53 PM
 * @Copyright (c) carterbrother
 */
public class User {

    private Long id ;
    private String userName;
    private SexEnum sex;
    private  String note;
    private  boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
