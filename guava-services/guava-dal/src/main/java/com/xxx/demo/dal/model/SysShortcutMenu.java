package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_shortcut_menu")
public class SysShortcutMenu {
    /**
     * 用户快捷菜单id
     */
    @Id
    @Column(name = "shortcut_menu_id")
    private String shortcutMenuId;

    /**
     * 用户快捷菜单名称
     */
    @Column(name = "shortcut_menu_name")
    private String shortcutMenuName;

    /**
     * 用户快捷菜单路径
     */
    @Column(name = "shortcut_menu_path")
    private String shortcutMenuPath;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 上级id
     */
    @Column(name = "shortcut_menu_parent_id")
    private String shortcutMenuParentId;

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
     * 获取用户快捷菜单id
     *
     * @return shortcut_menu_id - 用户快捷菜单id
     */
    public String getShortcutMenuId() {
        return shortcutMenuId;
    }

    /**
     * 设置用户快捷菜单id
     *
     * @param shortcutMenuId 用户快捷菜单id
     */
    public void setShortcutMenuId(String shortcutMenuId) {
        this.shortcutMenuId = shortcutMenuId;
    }

    /**
     * 获取用户快捷菜单名称
     *
     * @return shortcut_menu_name - 用户快捷菜单名称
     */
    public String getShortcutMenuName() {
        return shortcutMenuName;
    }

    /**
     * 设置用户快捷菜单名称
     *
     * @param shortcutMenuName 用户快捷菜单名称
     */
    public void setShortcutMenuName(String shortcutMenuName) {
        this.shortcutMenuName = shortcutMenuName;
    }

    /**
     * 获取用户快捷菜单路径
     *
     * @return shortcut_menu_path - 用户快捷菜单路径
     */
    public String getShortcutMenuPath() {
        return shortcutMenuPath;
    }

    /**
     * 设置用户快捷菜单路径
     *
     * @param shortcutMenuPath 用户快捷菜单路径
     */
    public void setShortcutMenuPath(String shortcutMenuPath) {
        this.shortcutMenuPath = shortcutMenuPath;
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
     * 获取上级id
     *
     * @return shortcut_menu_parent_id - 上级id
     */
    public String getShortcutMenuParentId() {
        return shortcutMenuParentId;
    }

    /**
     * 设置上级id
     *
     * @param shortcutMenuParentId 上级id
     */
    public void setShortcutMenuParentId(String shortcutMenuParentId) {
        this.shortcutMenuParentId = shortcutMenuParentId;
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