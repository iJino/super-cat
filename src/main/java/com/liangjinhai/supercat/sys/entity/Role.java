package com.liangjinhai.supercat.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Role implements Serializable {

    private Integer id;

    private String RoleName;

    private String name;

    private Integer parentRoleId;

    private String status;

    private Date createTime;
    //多对一
    private Role parentRole;
    //一对多
    private Set<Role> childRoles;

    private Set<Menu> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(Integer parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Role getParentRole() {
        return parentRole;
    }

    public void setParentRole(Role parentRole) {
        this.parentRole = parentRole;
    }

    public Set<Role> getChildRoles() {
        return childRoles;
    }

    public void setChildRoles(Set<Role> childRoles) {
        this.childRoles = childRoles;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", RoleName='" + RoleName + '\'' +
                ", name='" + name + '\'' +
                ", parentRoleId=" + parentRoleId +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", parentRole=" + parentRole +
                ", childRoles=" + childRoles +
                '}';
    }
}
