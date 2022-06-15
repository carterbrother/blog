package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 数据权限规则字典(DataRuleDict)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class DataRuleDict implements Serializable {
    private static final long serialVersionUID = -57402982151893031L;
    /**
    * id
    */
    private Long id;
    /**
    * 系统key
    */
    private String systemKey;
    /**
    * 数据权限规则名称
    */
    private String dataRuleName;
    /**
    * 数据权限规则key
    */
    private String dataRuleKey;
    /**
    * 字段
    */
    private String dataRuleColumn;
    /**
    * 条件
    */
    private String dataRuleConditions;
    /**
    * 规则值
    */
    private String dataRuleValue;
    /**
    * 接口id
    */
    private Long interfaceId;
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

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getDataRuleName() {
        return dataRuleName;
    }

    public void setDataRuleName(String dataRuleName) {
        this.dataRuleName = dataRuleName;
    }

    public String getDataRuleKey() {
        return dataRuleKey;
    }

    public void setDataRuleKey(String dataRuleKey) {
        this.dataRuleKey = dataRuleKey;
    }

    public String getDataRuleColumn() {
        return dataRuleColumn;
    }

    public void setDataRuleColumn(String dataRuleColumn) {
        this.dataRuleColumn = dataRuleColumn;
    }

    public String getDataRuleConditions() {
        return dataRuleConditions;
    }

    public void setDataRuleConditions(String dataRuleConditions) {
        this.dataRuleConditions = dataRuleConditions;
    }

    public String getDataRuleValue() {
        return dataRuleValue;
    }

    public void setDataRuleValue(String dataRuleValue) {
        this.dataRuleValue = dataRuleValue;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
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