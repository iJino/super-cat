package com.liangjinhai.supercat.sys.service.impl;

import com.liangjinhai.supercat.sys.entity.Menu;
import com.liangjinhai.supercat.sys.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Override
    public List<Menu> findAllPower() {
        return null;
    }
}
