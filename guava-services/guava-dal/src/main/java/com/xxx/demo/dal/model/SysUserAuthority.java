package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user_authority")
public class SysUserAuthority {
    /**
     * 用户权限表id
     */
    @Id
    @Column(name = "user_authority_id")
    private String userAuthorityId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 权限id
     */
    @Column(name = "authority_id")
    private String authorityId;

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
     * 获取用户权限表id
     *
     * @return user_authority_id - 用户权限表id
     */
    public String getUserAuthorityId() {
        return userAuthorityId;
    }

    /**
     * 设置用户权限表id
     *
     * @param userAuthorityId 用户权限表id
     */
    public void setUserAuthorityId(String userAuthorityId) {
        this.userAuthorityId = userAuthorityId;
    }

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
     * 获取权限id
     *
     * @return authority_id - 权限id
     */
    public String getAuthorityId() {
        return authorityId;
    }

    /**
     * 设置权限id
     *
     * @param authorityId 权限id
     */
    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
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