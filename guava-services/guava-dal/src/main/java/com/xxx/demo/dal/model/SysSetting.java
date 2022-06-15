package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_setting")
public class SysSetting {
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 系统名称
     */
    @Column(name = "sys_name")
    private String sysName;

    /**
     * 系统logo图标
     */
    @Column(name = "sys_logo")
    private String sysLogo;

    /**
     * 系统底部信息
     */
    @Column(name = "sys_bottom_text")
    private String sysBottomText;

    /**
     * 系统公告
     */
    @Column(name = "sys_notice_text")
    private String sysNoticeText;

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
     * 用户管理：初始、重置密码
     */
    @Column(name = "user_init_password")
    private String userInitPassword;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取系统名称
     *
     * @return sys_name - 系统名称
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * 设置系统名称
     *
     * @param sysName 系统名称
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    /**
     * 获取系统logo图标
     *
     * @return sys_logo - 系统logo图标
     */
    public String getSysLogo() {
        return sysLogo;
    }

    /**
     * 设置系统logo图标
     *
     * @param sysLogo 系统logo图标
     */
    public void setSysLogo(String sysLogo) {
        this.sysLogo = sysLogo;
    }

    /**
     * 获取系统底部信息
     *
     * @return sys_bottom_text - 系统底部信息
     */
    public String getSysBottomText() {
        return sysBottomText;
    }

    /**
     * 设置系统底部信息
     *
     * @param sysBottomText 系统底部信息
     */
    public void setSysBottomText(String sysBottomText) {
        this.sysBottomText = sysBottomText;
    }

    /**
     * 获取系统公告
     *
     * @return sys_notice_text - 系统公告
     */
    public String getSysNoticeText() {
        return sysNoticeText;
    }

    /**
     * 设置系统公告
     *
     * @param sysNoticeText 系统公告
     */
    public void setSysNoticeText(String sysNoticeText) {
        this.sysNoticeText = sysNoticeText;
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

    /**
     * 获取用户管理：初始、重置密码
     *
     * @return user_init_password - 用户管理：初始、重置密码
     */
    public String getUserInitPassword() {
        return userInitPassword;
    }

    /**
     * 设置用户管理：初始、重置密码
     *
     * @param userInitPassword 用户管理：初始、重置密码
     */
    public void setUserInitPassword(String userInitPassword) {
        this.userInitPassword = userInitPassword;
    }
}