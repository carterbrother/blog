package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 全局配置(GlobalConfig)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class GlobalConfig implements Serializable {
    private static final long serialVersionUID = -63749779182102585L;
    
    private Object id;
    /**
    * 配置名称
    */
    private String name;
    /**
    * 值
    */
    private String value;
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
    /**
    * 属性
    */
    private Integer properties;
    /**
    * 备注
    */
    private String remark;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Integer getProperties() {
        return properties;
    }

    public void setProperties(Integer properties) {
        this.properties = properties;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}