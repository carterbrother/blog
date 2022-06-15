package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 页面字典(对应后台系统的导航条)(PageDict)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class PageDict implements Serializable {
    private static final long serialVersionUID = -70961127467550072L;
    /**
    * id
    */
    private Long id;
    /**
    * 对应的系统key,本系统是account
    */
    private String systemKey;
    /**
    * 父级别id
    */
    private Long parentPageId;
    /**
    * 页面或者菜单编码
    */
    private String pageCode;
    /**
    * 页面或者菜单中文名
    */
    private String pageName;
    /**
    * 页面或者菜单url
    */
    private String pageUrl;
    /**
    * 层级
    */
    private Object pageLevel;
    /**
    * 1是0否
    */
    private Object menuFlag;
    /**
    * 1是0否
    */
    private Object lockedFlag;
    /**
    * 排序的整数值
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

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public Long getParentPageId() {
        return parentPageId;
    }

    public void setParentPageId(Long parentPageId) {
        this.parentPageId = parentPageId;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Object getPageLevel() {
        return pageLevel;
    }

    public void setPageLevel(Object pageLevel) {
        this.pageLevel = pageLevel;
    }

    public Object getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(Object menuFlag) {
        this.menuFlag = menuFlag;
    }

    public Object getLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(Object lockedFlag) {
        this.lockedFlag = lockedFlag;
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