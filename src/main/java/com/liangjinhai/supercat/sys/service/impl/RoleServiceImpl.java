package com.liangjinhai.supercat.sys.service.impl;

import com.liangjinhai.supercat.sys.entity.Role;
import com.liangjinhai.supercat.sys.mapper.RoleMapper;
import com.liangjinhai.supercat.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role getRolesById(Integer id) {
        return roleMapper.getRolesById(id);
    }
}
