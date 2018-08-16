package com.liangjinhai.supercat.sys.entity;


import java.util.Date;
import java.util.Set;

/**
 * @author: liangjinhai
 * @date: 2018/7/8 17:15
 * @decapitalize:  基本用户信息
 */
public class User {

    private Integer id;
    /**用户名*/
    private String username;
    /**联系电话*/
    private String mobilePhone;
    /**密码*/
    private String password;
    /**盐*/
    private String salt;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date modifyTime;
    /**停用时间*/
    private Date expiryTime;
    /**身份证*/
    private String idNum;
    /**状态*/
    private String status;
    /**创建人*/
    private Integer createby;

    private User parentUser;

    private Set<User> childUser;

    private Set<Role> roles;

    public User getParentUser() {
        return parentUser;
    }

    public void setParentUser(User parentUser) {
        this.parentUser = parentUser;
    }

    public Set<User> getChildUser() {
        return childUser;
    }

    public void setChildUser(Set<User> childUser) {
        this.childUser = childUser;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateby() {
        return createby;
    }

    public void setCreateby(int createby) {
        this.createby = createby;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }
}
