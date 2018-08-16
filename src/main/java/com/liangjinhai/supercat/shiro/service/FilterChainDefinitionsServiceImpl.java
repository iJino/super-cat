package com.liangjinhai.supercat.shiro.service;


import com.liangjinhai.supercat.sys.entity.Menu;
import com.liangjinhai.supercat.sys.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限过滤
 */
@Service("filterChainDefinitionsService")
public class FilterChainDefinitionsServiceImpl implements FilterChainDefinitionsService {


    @Resource
    private MenuService menuService;

    @Override
    public void reloadFilterChains(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        synchronized (shiroFilterFactoryBean) {   //强制同步，控制线程安全
            AbstractShiroFilter shiroFilter = null;

            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();

                PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
                        .getFilterChainResolver();
                // 过滤管理器
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
                // 清除权限配置
                manager.getFilterChains().clear();
                shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
                // 重新设置权限
                shiroFilterFactoryBean.setFilterChainDefinitionMap(this.loadFilterChainDefinitionMap());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Map<String, String> loadFilterChainDefinitionMap() {
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/supercat/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
//        filterChainDefinitionMap.put("/artdialog/**", "anon");
//        filterChainDefinitionMap.put("/images/**", "anon");
//        filterChainDefinitionMap.put("/adminlte/**", "anon");
//        filterChainDefinitionMap.put("/ws/**", "anon");
//        filterChainDefinitionMap.put("/soap/**", "anon");
//        filterChainDefinitionMap.put("/login_phone", "anon");
        filterChainDefinitionMap.put("/login", "shiroFilter");
//        filterChainDefinitionMap.put("/logout", "anon");
//        filterChainDefinitionMap.put("/err/**", "anon");
//        filterChainDefinitionMap.put("/evaluation/**", "anon");
//
//        filterChainDefinitionMap.put("/order/**", "authc");
//        filterChainDefinitionMap.put("/change/**", "authc");
//        filterChainDefinitionMap.put("/teamOrder/**", "authc");
//        filterChainDefinitionMap.put("/buy/saveRouting", "authc");//保存对应航段信息
//        filterChainDefinitionMap.put("/buy/checkPrice", "authc");//验价

        List<Menu> menus = menuService.findAllPower();
        Map<String, List<String>> map = new LinkedHashMap<>();
        for (Menu menu : menus) {
            if (StringUtils.isNotBlank(menu.getMenuValue())) {
                String value = null;
                if (null != menu.getHttpMethod()) {
                    value = String.format("%s:%s", menu.getMenuKey(), menu.getHttpMethod());
                    List list = map.get(menu.getMenuValue());
                    if (null == list) list = new ArrayList();
                    list.add(value);
                    map.put(menu.getMenuValue(), list);
                } else {
                    filterChainDefinitionMap.put(menu.getMenuValue(), String.format("shiroFilter,perms[%s]", menu.getMenuKey()));
                }
            }

        }
        for (Map.Entry<String, List<String>> val : map.entrySet()) {
            filterChainDefinitionMap.put(val.getKey(), "shiroFilter,httpMethodAnyPerms[" + StringUtils.join(val.getValue(), "|") + "]");
        }
        filterChainDefinitionMap.put("/**", "shiroFilter");

        return filterChainDefinitionMap;
    }


}
