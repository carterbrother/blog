package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统角色(RoleDict)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class RoleDict implements Serializable {
    private static final long serialVersionUID = 374027988157245953L;
    /**
    * id
    */
    private Long id;
    /**
    * 角色英语名
    */
    private String roleKey;
    /**
    * 角色中文名
    */
    private String roleValue;
    /**
    * 系统英文名
    */
    private String systemKey;
    /**
    * 排序整数值
    */
    private Integer orderIntValue;
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

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public Integer getOrderIntValue() {
        return orderIntValue;
    }

    public void setOrderIntValue(Integer orderIntValue) {
        this.orderIntValue = orderIntValue;
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