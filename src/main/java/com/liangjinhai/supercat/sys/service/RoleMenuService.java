package com.liangjinhai.supercat.sys.service;

import com.liangjinhai.supercat.sys.entity.RoleMenu;
import com.liangjinhai.supercat.sys.entity.User;

import java.util.List;

public interface RoleMenuService {

    Object findRoleMenusByUserId(User user);

    Object findPowersByUserId(User user);

    List<RoleMenu> findPowersByUser(User user);
}
