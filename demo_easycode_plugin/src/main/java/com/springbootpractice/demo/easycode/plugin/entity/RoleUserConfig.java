package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色用户对应关系(RoleUserConfig)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class RoleUserConfig implements Serializable {
    private static final long serialVersionUID = -61322241827631252L;
    /**
    * id
    */
    private Long id;
    /**
    * 角色id
    */
    private Long roleId;
    /**
    * 系统key
    */
    private String systemKey;
    /**
    * 用户id
    */
    private Long userId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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