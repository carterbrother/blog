package com.springbootpractice.demo.easycode.plugin.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户操作日志表(UserLog)实体类
 *
 * @author carter
 * @since 2020-02-14 22:01:32
 */
public class UserLog implements Serializable {
    private static final long serialVersionUID = -39098180295765749L;
    /**
    * id
    */
    private Long id;
    /**
    * 请求id
    */
    private String requestId;
    /**
    * 日志输入内容
    */
    private Object logRequest;
    /**
    * 日志输出内容
    */
    private Object logResponse;
    /**
    * 日志级别
    */
    private Object logLevel;
    /**
    * IP
    */
    private String ipAddress;
    /**
    * 操作类型
    */
    private Object operateType;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 用户账号
    */
    private String userName;
    /**
    * 真实名字
    */
    private String realName;
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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getLogRequest() {
        return logRequest;
    }

    public void setLogRequest(Object logRequest) {
        this.logRequest = logRequest;
    }

    public Object getLogResponse() {
        return logResponse;
    }

    public void setLogResponse(Object logResponse) {
        this.logResponse = logResponse;
    }

    public Object getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Object logLevel) {
        this.logLevel = logLevel;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Object getOperateType() {
        return operateType;
    }

    public void setOperateType(Object operateType) {
        this.operateType = operateType;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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