package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_authority")
public class SysAuthority {
    /**
     * 权限id
     */
    @Id
    @Column(name = "authority_id")
    private String authorityId;

    /**
     * 权限名称，ROLE_开头，全大写
     */
    @Column(name = "authority_name")
    private String authorityName;

    /**
     * 权限描述
     */
    @Column(name = "authority_remark")
    private String authorityRemark;

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
     * 权限内容，可访问的url，多个时用,隔开
     */
    @Column(name = "authority_content")
    private String authorityContent;

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
     * 获取权限名称，ROLE_开头，全大写
     *
     * @return authority_name - 权限名称，ROLE_开头，全大写
     */
    public String getAuthorityName() {
        return authorityName;
    }

    /**
     * 设置权限名称，ROLE_开头，全大写
     *
     * @param authorityName 权限名称，ROLE_开头，全大写
     */
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    /**
     * 获取权限描述
     *
     * @return authority_remark - 权限描述
     */
    public String getAuthorityRemark() {
        return authorityRemark;
    }

    /**
     * 设置权限描述
     *
     * @param authorityRemark 权限描述
     */
    public void setAuthorityRemark(String authorityRemark) {
        this.authorityRemark = authorityRemark;
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
     * 获取权限内容，可访问的url，多个时用,隔开
     *
     * @return authority_content - 权限内容，可访问的url，多个时用,隔开
     */
    public String getAuthorityContent() {
        return authorityContent;
    }

    /**
     * 设置权限内容，可访问的url，多个时用,隔开
     *
     * @param authorityContent 权限内容，可访问的url，多个时用,隔开
     */
    public void setAuthorityContent(String authorityContent) {
        this.authorityContent = authorityContent;
    }
}