package com.carterbrother.springbootpractice.chapter3_demo.pojo;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 普通的pojo类
 * @date 2019年02月14日 6:09 PM
 * @Copyright (c) carterbrother
 */
public class User {
    private Long id;
    private String userName;
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
