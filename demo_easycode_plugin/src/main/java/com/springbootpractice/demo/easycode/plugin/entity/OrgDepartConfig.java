package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 公司的部门配置(OrgDepartConfig)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class OrgDepartConfig implements Serializable {
    private static final long serialVersionUID = -56568034777721952L;
    /**
    * id
    */
    private Long id;
    /**
    * 机构id
    */
    private Long orgId;
    /**
    * 父部门id
    */
    private Long parentDepartId;
    /**
    * 部门key
    */
    private String departKey;
    /**
    * 部门名称
    */
    private String departValue;
    /**
    * 部门层级
    */
    private Object departLevel;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getParentDepartId() {
        return parentDepartId;
    }

    public void setParentDepartId(Long parentDepartId) {
        this.parentDepartId = parentDepartId;
    }

    public String getDepartKey() {
        return departKey;
    }

    public void setDepartKey(String departKey) {
        this.departKey = departKey;
    }

    public String getDepartValue() {
        return departValue;
    }

    public void setDepartValue(String departValue) {
        this.departValue = departValue;
    }

    public Object getDepartLevel() {
        return departLevel;
    }

    public void setDepartLevel(Object departLevel) {
        this.departLevel = departLevel;
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