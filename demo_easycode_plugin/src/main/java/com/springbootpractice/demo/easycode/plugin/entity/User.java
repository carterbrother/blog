package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户基础信息(User)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class User implements Serializable {
    private static final long serialVersionUID = -83570479900601305L;
    /**
    * 主键。自增长
    */
    private Integer id;
    /**
    * 运营商编码
    */
    private String carrierCode;
    /**
    * 乐心号
    */
    private String lifesenseId;
    /**
    * 姓名
    */
    private String name;
    /**
    * 头像
    */
    private String headImg;
    /**
    * 性别：男=1；女=2
    */
    private Object sex;
    /**
    * 出生日期
    */
    private Object birthday;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 身份证号
    */
    private String idcard;
    /**
    * 社保号
    */
    private String insuranceId;
    /**
    * 身高（单位：cm）
    */
    private Object height;
    /**
    * 腰围（单位：cm）
    */
    private Object waist;
    /**
    * 微信UnionId
    */
    private String wechatUnionId;
    /**
    * 更新时间戳
    */
    private Long updated;
    /**
    * 创建时间戳
    */
    private Date created;
    /**
    * 客户端ID
    */
    private String clientId;
    /**
    * 删除标识：正常=0，删除=1
    */
    private Object deleted;
    
    private String appVersion;
    
    private String country;
    
    private String province;
    
    private String city;
    /**
    * 体重单位:0/null-千克;1-千克;2-斤;3-磅;4-英石
    */
    private Object weightUnit;
    /**
    * 长度单位:0/null-公制;1-公制;2-英制
    */
    private Object lengthUnit;
    /**
    * 注册邀请码
    */
    private String invitationCode;
    /**
    * 紧急联系人姓名
    */
    private String urgentLinkRealName;
    /**
    * 紧急联系人手机号
    */
    private String urgentLinkMobile;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getLifesenseId() {
        return lifesenseId;
    }

    public void setLifesenseId(String lifesenseId) {
        this.lifesenseId = lifesenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public Object getWaist() {
        return waist;
    }

    public void setWaist(Object waist) {
        this.waist = waist;
    }

    public String getWechatUnionId() {
        return wechatUnionId;
    }

    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(Object weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Object getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(Object lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getUrgentLinkRealName() {
        return urgentLinkRealName;
    }

    public void setUrgentLinkRealName(String urgentLinkRealName) {
        this.urgentLinkRealName = urgentLinkRealName;
    }

    public String getUrgentLinkMobile() {
        return urgentLinkMobile;
    }

    public void setUrgentLinkMobile(String urgentLinkMobile) {
        this.urgentLinkMobile = urgentLinkMobile;
    }

}