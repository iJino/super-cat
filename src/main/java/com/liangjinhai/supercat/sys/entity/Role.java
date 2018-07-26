package com.liangjinhai.supercat.sys.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Role {

    private Integer id;

    private String RoleName;

    private String name;

    private Integer parentRoleId;

    private String status;

    private Date createTime;
    //多对一
    private Role parentRole;
    //一对多
    private List<Role> childRoles;

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

    public List<Role> getChildRoles() {
        return childRoles;
    }

    public void setChildRoles(List<Role> childRoles) {
        this.childRoles = childRoles;
    }
}
