package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user_menu")
public class SysUserMenu {
    /**
     * 用户菜单表id
     */
    @Id
    @Column(name = "user_menu_id")
    private String userMenuId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private String menuId;

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
     * 获取用户菜单表id
     *
     * @return user_menu_id - 用户菜单表id
     */
    public String getUserMenuId() {
        return userMenuId;
    }

    /**
     * 设置用户菜单表id
     *
     * @param userMenuId 用户菜单表id
     */
    public void setUserMenuId(String userMenuId) {
        this.userMenuId = userMenuId;
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
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
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