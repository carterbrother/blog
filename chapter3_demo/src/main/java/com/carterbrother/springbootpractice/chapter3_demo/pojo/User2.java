package com.carterbrother.springbootpractice.chapter3_demo.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 普通的pojo类
 * @date 2019年02月14日 6:09 PM
 * @Copyright (c) carterbrother
 */
@Component(value = "user2")
public class User2 {
    @Value("10087")
    private Long id;
    @Value("userName_#{user2.id}")
    private String userName;
    @Value("note_#{user2.userName}")
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
