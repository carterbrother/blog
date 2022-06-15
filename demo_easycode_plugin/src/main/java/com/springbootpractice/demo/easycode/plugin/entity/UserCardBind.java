package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserCardBind)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserCardBind implements Serializable {
    private static final long serialVersionUID = 893799075054036760L;
    
    private Long id;
    
    private Long userId;
    
    private String icCardNo;
    
    private Date bindTime;
    
    private String deviceId;
    
    private String remark;


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

    public String getIcCardNo() {
        return icCardNo;
    }

    public void setIcCardNo(String icCardNo) {
        this.icCardNo = icCardNo;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}