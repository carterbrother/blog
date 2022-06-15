package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 第三方账号(UserThirdpartyAccount)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserThirdpartyAccount implements Serializable {
    private static final long serialVersionUID = 766620133699400858L;
    /**
    * 主键。自增长
    */
    private Integer id;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * APP类型
    */
    private Object appType;
    /**
    * 账号类型。wechat=1;qq=2
    */
    private Object accountType;
    /**
    * 第三方账号OPENID
    */
    private String openId;
    /**
    * 启用状态。禁用=0；启用=1
    */
    private Object enabled;
    /**
    * 微信公众号ID
    */
    private String wechatServiceNo;
    
    private Date createdAt;
    
    private Long updatedAt;
    /**
    * 最后授权的appId
    */
    private String lastAppId;


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

    public Object getAppType() {
        return appType;
    }

    public void setAppType(Object appType) {
        this.appType = appType;
    }

    public Object getAccountType() {
        return accountType;
    }

    public void setAccountType(Object accountType) {
        this.accountType = accountType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Object getEnabled() {
        return enabled;
    }

    public void setEnabled(Object enabled) {
        this.enabled = enabled;
    }

    public String getWechatServiceNo() {
        return wechatServiceNo;
    }

    public void setWechatServiceNo(String wechatServiceNo) {
        this.wechatServiceNo = wechatServiceNo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastAppId() {
        return lastAppId;
    }

    public void setLastAppId(String lastAppId) {
        this.lastAppId = lastAppId;
    }

}