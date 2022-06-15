package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 接口配置(InterfaceConfig)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class InterfaceConfig implements Serializable {
    private static final long serialVersionUID = 404488050004796261L;
    /**
    * id
    */
    private Long id;
    /**
    * 接口名称
    */
    private String interfaceName;
    /**
    * 接口编码
    */
    private String interfaceCode;
    /**
    * URL
    */
    private String interfaceUrl;
    /**
    * 对应的控制器方法
    */
    private String interfaceMethod;
    /**
    * 排序整数值
    */
    private Integer orderIntValue;
    /**
    * 接口所属页面ID
    */
    private Long pageId;
    /**
    * 1是0否
    */
    private Object buttonFlag;
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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public String getInterfaceMethod() {
        return interfaceMethod;
    }

    public void setInterfaceMethod(String interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }

    public Integer getOrderIntValue() {
        return orderIntValue;
    }

    public void setOrderIntValue(Integer orderIntValue) {
        this.orderIntValue = orderIntValue;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Object getButtonFlag() {
        return buttonFlag;
    }

    public void setButtonFlag(Object buttonFlag) {
        this.buttonFlag = buttonFlag;
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