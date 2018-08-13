package com.liangjinhai.supercat.sys.mapper;

import com.liangjinhai.supercat.sys.entity.Role;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

public interface RoleMapper {
    Role getRolesById(Integer id);

    Set<String> findAllByuserId(Integer id);
}
