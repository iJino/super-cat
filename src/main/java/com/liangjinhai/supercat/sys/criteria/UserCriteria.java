package com.liangjinhai.supercat.sys.criteria;

import com.liangjinhai.supercat.sys.entity.User;

public class UserCriteria extends BaseCriteria{
    private User user = new User();

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
