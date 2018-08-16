package com.liangjinhai.supercat.sys.mapper;

import com.liangjinhai.supercat.sys.entity.Menu;
import com.liangjinhai.supercat.sys.enums.MenuType;

import java.util.List;

public interface MenuMapper {

    List<Menu> findAllByType(List<String> typeList);
}
