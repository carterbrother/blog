package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户登录表(UserLogin)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserLogin implements Serializable {
    private static final long serialVersionUID = 129247181580787685L;
    /**
    * id
    */
    private Long id;
    /**
    * 显示给用户的id
    */
    private Long userId;
    /**
    * 账号，初期是手机号
    */
    private String userName;
    /**
    * 密码
    */
    private String password;
    /**
    * 手机号
    */
    private String mobile;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 真实姓名
    */
    private String realName;
    /**
    * 昵称
    */
    private String nickName;
    /**
    * 客户端ID
    */
    private String clientId;
    /**
    * 1是0否
    */
    private Object lockedFlag;
    /**
    * im系统的用户id
    */
    private String imUserId;
    /**
    * sport系统的用户id
    */
    private Long sportUserId;
    /**
    * 系统编号
    */
    private Integer systemType;
    /**
    * 所属部门ID
    */
    private Long departId;
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
    * 截止有效日期
    */
    private Date endLoginDate;
    /**
    * 真实姓名对应的拼音首字母
    */
    private String realNamePinyin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Object getLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(Object lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }

    public Long getSportUserId() {
        return sportUserId;
    }

    public void setSportUserId(Long sportUserId) {
        this.sportUserId = sportUserId;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
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

    public Date getEndLoginDate() {
        return endLoginDate;
    }

    public void setEndLoginDate(Date endLoginDate) {
        this.endLoginDate = endLoginDate;
    }

    public String getRealNamePinyin() {
        return realNamePinyin;
    }

    public void setRealNamePinyin(String realNamePinyin) {
        this.realNamePinyin = realNamePinyin;
    }

}