package com.liangjinhai.supercat.sys.entity;

import com.liangjinhai.supercat.shiro.ShiroHttpMethodPermissionFilter;
import com.liangjinhai.supercat.sys.enums.MenuType;
import com.liangjinhai.supercat.sys.enums.Status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Menu {

    private Integer id;

    private String name;

    private String menuKey;

    private MenuType type;

    private String menuValue;

    /**
     * 父节点
     */
    private Menu parentMenu;
    /**
     * 子节点
     */
    private Set<Menu> menus = new HashSet<>();

    private Status status;

    private ShiroHttpMethodPermissionFilter.HttpMethod httpMethod;

    private Date createTime = new Date();

    private Integer sortId = 200;

    private Set<RoleMenu> roleMenus = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getMenuValue() {
        return menuValue;
    }

    public void setMenuValue(String menuValue) {
        this.menuValue = menuValue;
    }


    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public Set<RoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Set<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    public ShiroHttpMethodPermissionFilter.HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(ShiroHttpMethodPermissionFilter.HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
    //
    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }
}

