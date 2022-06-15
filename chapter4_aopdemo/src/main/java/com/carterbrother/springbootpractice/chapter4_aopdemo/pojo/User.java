package com.carterbrother.springbootpractice.chapter4_aopdemo.pojo;

import java.time.LocalDate;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年02月19日 3:27 PM
 * @Copyright (c) carterbrother
 */
public class User {

    private Long id;
    private String userName;
    private LocalDate birthday;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append('}');
        return sb.toString();
    }
}
