package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 小程序进入的公众号记录(UserLoginPublicwechat)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserLoginPublicwechat implements Serializable {
    private static final long serialVersionUID = -27278817197435513L;
    
    private Long id;
    /**
    * userLogin的id
    */
    private Long userId;
    /**
    * 小程序的openId
    */
    private String openId;
    /**
    * 机构公众号的appId
    */
    private String appIdStr;
    /**
    * 机构公众号下的openId
    */
    private String orgOpenId;
    /**
    * 对应app_type系统类型
    */
    private Integer appType;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAppIdStr() {
        return appIdStr;
    }

    public void setAppIdStr(String appIdStr) {
        this.appIdStr = appIdStr;
    }

    public String getOrgOpenId() {
        return orgOpenId;
    }

    public void setOrgOpenId(String orgOpenId) {
        this.orgOpenId = orgOpenId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
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