package com.liangjinhai.supercat.shiro.service;



import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/3.
 */
public interface FilterChainDefinitionsService {

    /**
     * 重新加载权限过滤
     *
     * @param shiroFilterFactoryBean
     */
    void reloadFilterChains(ShiroFilterFactoryBean shiroFilterFactoryBean);

    /**
     * 加载FilterChainDefinitionMap
     *
     * @return
     */
    Map<String, String> loadFilterChainDefinitionMap();

}
