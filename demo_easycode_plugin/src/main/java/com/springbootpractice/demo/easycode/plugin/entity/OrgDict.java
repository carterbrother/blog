package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 机构或者医院(OrgDict)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class OrgDict implements Serializable {
    private static final long serialVersionUID = -62764157934689532L;
    /**
    * id
    */
    private Long id;
    /**
    * 公司的英语名称
    */
    private String orgKey;
    /**
    * 公司名称
    */
    private String orgValue;
    /**
    * 商务手机号
    */
    private String bizerMobile;
    /**
    * 商务对接人
    */
    private String bizerName;
    /**
    * 公司地址
    */
    private String orgAddress;
    /**
    * 机构或者医院代号
    */
    private String orgCode;
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
    /**
    * appType
    */
    private Integer systemType;
    /**
    * remrelation.app_dict公众号配置的主键ID
    */
    private Long appId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgKey() {
        return orgKey;
    }

    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    public String getOrgValue() {
        return orgValue;
    }

    public void setOrgValue(String orgValue) {
        this.orgValue = orgValue;
    }

    public String getBizerMobile() {
        return bizerMobile;
    }

    public void setBizerMobile(String bizerMobile) {
        this.bizerMobile = bizerMobile;
    }

    public String getBizerName() {
        return bizerName;
    }

    public void setBizerName(String bizerName) {
        this.bizerName = bizerName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

}