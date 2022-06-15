package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 灰度名单(GrayList)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class GrayList implements Serializable {
    private static final long serialVersionUID = -24502902503638761L;
    
    private Object id;
    /**
    * 登录ID
    */
    private String loginId;
    /**
    * 创建时间
    */
    private Date created;
    /**
    * 更新时间
    */
    private Date updated;
    /**
    * 删除状态
    */
    private Object deleted;


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