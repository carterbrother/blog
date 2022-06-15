package com.xxx.demo.dal.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu {
    /**
     * 菜单id
     */
    @Id
    @Column(name = "menu_id")
    private String menuId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单路径
     */
    @Column(name = "menu_path")
    private String menuPath;

    /**
     * 上级id
     */
    @Column(name = "menu_parent_id")
    private String menuParentId;

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
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单路径
     *
     * @return menu_path - 菜单路径
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * 设置菜单路径
     *
     * @param menuPath 菜单路径
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /**
     * 获取上级id
     *
     * @return menu_parent_id - 上级id
     */
    public String getMenuParentId() {
        return menuParentId;
    }

    /**
     * 设置上级id
     *
     * @param menuParentId 上级id
     */
    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
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