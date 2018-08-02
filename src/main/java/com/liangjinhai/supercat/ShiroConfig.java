package com.liangjinhai.supercat;

import com.liangjinhai.supercat.shiro.ShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public AuthorizingRealm getShiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

    @Bean
    public DefaultSecurityManager getSecurityManager(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(getShiroRealm());
        return securityManager;
    }

    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{getSecurityManager()});
        return factoryBean;
    }
}
