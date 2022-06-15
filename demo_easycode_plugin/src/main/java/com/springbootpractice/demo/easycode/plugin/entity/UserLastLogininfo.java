package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户最后登录信息(UserLastLogininfo)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserLastLogininfo implements Serializable {
    private static final long serialVersionUID = -73834853964724541L;
    /**
    * 主键id,对应用户的id
    */
    private Long id;
    /**
    * 最后登录的客户端id
    */
    private String lastClientId;
    /**
    * 最后登录时间
    */
    private Date lastLoginTime;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 修改时间
    */
    private Date updated;
    /**
    * 1删除0未删除
    */
    private Object deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastClientId() {
        return lastClientId;
    }

    public void setLastClientId(String lastClientId) {
        this.lastClientId = lastClientId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

}