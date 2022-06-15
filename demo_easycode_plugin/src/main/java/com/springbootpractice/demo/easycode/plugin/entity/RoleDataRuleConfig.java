package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色权限项对应关系(RoleDataRuleConfig)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class RoleDataRuleConfig implements Serializable {
    private static final long serialVersionUID = -80359156155914242L;
    /**
    * id
    */
    private Long id;
    /**
    * 角色id
    */
    private Long roleId;
    /**
    * 接口数据权限规则id
    */
    private Long dataRuleId;
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

    public Long getDataRuleId() {
        return dataRuleId;
    }

    public void setDataRuleId(Long dataRuleId) {
        this.dataRuleId = dataRuleId;
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