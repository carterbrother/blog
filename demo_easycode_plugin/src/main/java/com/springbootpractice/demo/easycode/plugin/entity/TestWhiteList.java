package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TestWhiteList)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class TestWhiteList implements Serializable {
    private static final long serialVersionUID = -70662409254386392L;
    
    private Object id;
    /**
    * 用户ID
    */
    private String loginId;
    /**
    * 真实姓名
    */
    private String realname;
    /**
    * 应用类型
    */
    private Object apptype;
    /**
    * 状态
    */
    private Object status;
    /**
    * 删除状态
    */
    private Object deleted;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 更新时间
    */
    private Date updated;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Object getApptype() {
        return apptype;
    }

    public void setApptype(Object apptype) {
        this.apptype = apptype;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
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

}