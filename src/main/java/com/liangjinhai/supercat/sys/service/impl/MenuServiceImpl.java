package com.liangjinhai.supercat.sys.service.impl;

import com.liangjinhai.supercat.sys.entity.Menu;
import com.liangjinhai.supercat.sys.enums.MenuType;
import com.liangjinhai.supercat.sys.mapper.MenuMapper;
import com.liangjinhai.supercat.sys.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllPower() {
        return menuMapper.findAllByType(Arrays.asList(MenuType.POWER.name(), MenuType.URL.name()));
    }
}
