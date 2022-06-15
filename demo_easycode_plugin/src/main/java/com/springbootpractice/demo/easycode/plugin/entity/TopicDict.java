package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 研究课题字典表(TopicDict)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class TopicDict implements Serializable {
    private static final long serialVersionUID = 325820271485919306L;
    /**
    * id
    */
    private Long id;
    /**
    * 课题名称
    */
    private String topicName;
    /**
    * 父课题ID
    */
    private Long parentId;
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

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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