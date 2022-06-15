package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 机构跟医生的关系(OrgDoctorConfig)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:30
 */
public class OrgDoctorConfig implements Serializable {
    private static final long serialVersionUID = -78236862864033384L;
    
    private Long id;
    /**
    * 机构ID
    */
    private Long orgId;
    /**
    * app_dict对应的主键ID,标识机构对应的企业公众号,做冗余
    */
    private Long appId;
    /**
    * 医生ID
    */
    private Long doctorId;
    /**
    * 医生在对应的公众号下的二维码
    */
    private String doctorBarCode;
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorBarCode() {
        return doctorBarCode;
    }

    public void setDoctorBarCode(String doctorBarCode) {
        this.doctorBarCode = doctorBarCode;
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