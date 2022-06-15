package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 医生信息(UserDoctorInfo)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserDoctorInfo implements Serializable {
    private static final long serialVersionUID = -63222364417831350L;
    /**
    * 用户id
    */
    private Long id;
    /**
    * 职称
    */
    private String jobTitle;
    /**
    * 医院名称
    */
    private String orgName;
    /**
    * 科室名称
    */
    private String departmentName;
    /**
    * 地址
    */
    private String orgAddress;
    /**
    * 所属课题
    */
    private String topicJson;
    /**
    * 擅长领域
    */
    private String skillDesc;
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
    * 执业照片1
    */
    private String urlZy1;
    /**
    * 执业照片2
    */
    private String urlZy2;
    /**
    * 资格照片1
    */
    private String urlZg1;
    /**
    * 资格照片2
    */
    private String urlZg2;
    /**
    * 个人介绍，医生端app用到
    */
    private String selfDesc;
    /**
    * 认证状态 -1资料不全 0 待审核 1 审核通过
    */
    private Object confirmStatus;
    /**
    * 身份证图片url
    */
    private String idcardUrl;
    /**
    * 从业日期
    */
    private Object startWorkDate;
    /**
    * 身份证背面照片url
    */
    private String idcardBackUrl;
    /**
    * 审核不通过的原因备注
    */
    private String memo;
    /**
    * 申请审核事件
    */
    private Date applyTime;
    /**
    * 科室ID
    */
    private String departmentId;
    /**
    * 医院的ID
    */
    private String hospitalId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getTopicJson() {
        return topicJson;
    }

    public void setTopicJson(String topicJson) {
        this.topicJson = topicJson;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
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

    public String getUrlZy1() {
        return urlZy1;
    }

    public void setUrlZy1(String urlZy1) {
        this.urlZy1 = urlZy1;
    }

    public String getUrlZy2() {
        return urlZy2;
    }

    public void setUrlZy2(String urlZy2) {
        this.urlZy2 = urlZy2;
    }

    public String getUrlZg1() {
        return urlZg1;
    }

    public void setUrlZg1(String urlZg1) {
        this.urlZg1 = urlZg1;
    }

    public String getUrlZg2() {
        return urlZg2;
    }

    public void setUrlZg2(String urlZg2) {
        this.urlZg2 = urlZg2;
    }

    public String getSelfDesc() {
        return selfDesc;
    }

    public void setSelfDesc(String selfDesc) {
        this.selfDesc = selfDesc;
    }

    public Object getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Object confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getIdcardUrl() {
        return idcardUrl;
    }

    public void setIdcardUrl(String idcardUrl) {
        this.idcardUrl = idcardUrl;
    }

    public Object getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Object startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getIdcardBackUrl() {
        return idcardBackUrl;
    }

    public void setIdcardBackUrl(String idcardBackUrl) {
        this.idcardBackUrl = idcardBackUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

}