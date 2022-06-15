package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserCardBindHis)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserCardBindHis implements Serializable {
    private static final long serialVersionUID = -76974994553613934L;
    
    private Integer logId;
    
    private Integer userId;
    
    private String openId;
    
    private String icCardNo;
    
    private Integer operType;
    
    private Date operTime;
    
    private String deviceId;
    
    private String remark;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIcCardNo() {
        return icCardNo;
    }

    public void setIcCardNo(String icCardNo) {
        this.icCardNo = icCardNo;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
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