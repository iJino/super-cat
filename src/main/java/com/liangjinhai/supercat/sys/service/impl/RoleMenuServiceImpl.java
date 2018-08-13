package com.liangjinhai.supercat.sys.service.impl;

import com.liangjinhai.supercat.sys.entity.RoleMenu;
import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
    @Override
    public Object findRoleMenusByUserId(User user) {
        return null;
    }

    @Override
    public Object findPowersByUserId(User user) {
        return null;
    }

    @Override
    public List<RoleMenu> findPowersByUser(User user) {
        return null;
    }
}
