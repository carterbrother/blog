package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户属性表(UserAttribute)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserAttribute implements Serializable {
    private static final long serialVersionUID = 587150060654069165L;
    /**
    * 主键。自增长
    */
    private Integer id;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 属性名
    */
    private String attriKey;
    /**
    * 属性值
    */
    private String attriValue;
    /**
    * 描述
    */
    private String attriDesc;
    
    private Date createTime;
    
    private Date updateTime;


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

    public String getAttriKey() {
        return attriKey;
    }

    public void setAttriKey(String attriKey) {
        this.attriKey = attriKey;
    }

    public String getAttriValue() {
        return attriValue;
    }

    public void setAttriValue(String attriValue) {
        this.attriValue = attriValue;
    }

    public String getAttriDesc() {
        return attriDesc;
    }

    public void setAttriDesc(String attriDesc) {
        this.attriDesc = attriDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}