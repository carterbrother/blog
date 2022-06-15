package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 我管理的用户(MyManagedUser)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class MyManagedUser implements Serializable {
    private static final long serialVersionUID = -27548850354460801L;
    /**
    * 主键。自增长
    */
    private Integer id;
    /**
    * 我的用户ID
    */
    private Integer userId;
    /**
    * 被管理的用户ID
    */
    private Integer managedUserId;
    /**
    * 删除标识。未删除=0；1=已删除；
    */
    private Object deleted;
    /**
    * 更新时间戳
    */
    private Long updated;
    /**
    * 创建时间戳
    */
    private Date created;
    /**
    * 客户端ID
    */
    private String clientId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getManagedUserId() {
        return managedUserId;
    }

    public void setManagedUserId(Integer managedUserId) {
        this.managedUserId = managedUserId;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}