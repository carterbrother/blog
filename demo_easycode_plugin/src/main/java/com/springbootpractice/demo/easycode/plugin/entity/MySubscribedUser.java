package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 我关注的用户(MySubscribedUser)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class MySubscribedUser implements Serializable {
    private static final long serialVersionUID = 931138989889550704L;
    /**
    * 主键。自增长
    */
    private Integer id;
    /**
    * 我的用户ID
    */
    private Integer userId;
    /**
    * 被关注用户ID
    */
    private Integer subscribedUserId;
    /**
    * 被关注用户的管理者ID
    */
    private Integer managerUserId;
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
    /**
    * 删除标识：正常=0，删除=1
    */
    private Object deleted;
    
    private String remark;


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

    public Integer getSubscribedUserId() {
        return subscribedUserId;
    }

    public void setSubscribedUserId(Integer subscribedUserId) {
        this.subscribedUserId = subscribedUserId;
    }

    public Integer getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
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

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}