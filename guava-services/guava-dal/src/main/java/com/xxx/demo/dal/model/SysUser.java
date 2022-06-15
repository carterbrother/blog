package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 软删除标识，Y/N
     */
    private String valid;

    /**
     * 限制允许登录的IP集合
     */
    @Column(name = "limited_ip")
    private String limitedIp;

    /**
     * 账号失效时间，超过时间将不能登录系统
     */
    @Column(name = "expired_time")
    private Date expiredTime;

    /**
     * 最近修改密码时间，超出时间间隔，提示用户修改密码
     */
    @Column(name = "last_change_pwd_time")
    private Date lastChangePwdTime;

    /**
     * 是否允许账号同一个时刻多人在线，Y/N
     */
    @Column(name = "limit_multi_login")
    private String limitMultiLogin;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取软删除标识，Y/N
     *
     * @return valid - 软删除标识，Y/N
     */
    public String getValid() {
        return valid;
    }

    /**
     * 设置软删除标识，Y/N
     *
     * @param valid 软删除标识，Y/N
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * 获取限制允许登录的IP集合
     *
     * @return limited_ip - 限制允许登录的IP集合
     */
    public String getLimitedIp() {
        return limitedIp;
    }

    /**
     * 设置限制允许登录的IP集合
     *
     * @param limitedIp 限制允许登录的IP集合
     */
    public void setLimitedIp(String limitedIp) {
        this.limitedIp = limitedIp;
    }

    /**
     * 获取账号失效时间，超过时间将不能登录系统
     *
     * @return expired_time - 账号失效时间，超过时间将不能登录系统
     */
    public Date getExpiredTime() {
        return expiredTime;
    }

    /**
     * 设置账号失效时间，超过时间将不能登录系统
     *
     * @param expiredTime 账号失效时间，超过时间将不能登录系统
     */
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    /**
     * 获取最近修改密码时间，超出时间间隔，提示用户修改密码
     *
     * @return last_change_pwd_time - 最近修改密码时间，超出时间间隔，提示用户修改密码
     */
    public Date getLastChangePwdTime() {
        return lastChangePwdTime;
    }

    /**
     * 设置最近修改密码时间，超出时间间隔，提示用户修改密码
     *
     * @param lastChangePwdTime 最近修改密码时间，超出时间间隔，提示用户修改密码
     */
    public void setLastChangePwdTime(Date lastChangePwdTime) {
        this.lastChangePwdTime = lastChangePwdTime;
    }

    /**
     * 获取是否允许账号同一个时刻多人在线，Y/N
     *
     * @return limit_multi_login - 是否允许账号同一个时刻多人在线，Y/N
     */
    public String getLimitMultiLogin() {
        return limitMultiLogin;
    }

    /**
     * 设置是否允许账号同一个时刻多人在线，Y/N
     *
     * @param limitMultiLogin 是否允许账号同一个时刻多人在线，Y/N
     */
    public void setLimitMultiLogin(String limitMultiLogin) {
        this.limitMultiLogin = limitMultiLogin;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}