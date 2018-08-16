package com.liangjinhai.supercat;

import com.liangjinhai.supercat.shiro.ShiroFormAuthenticationFilter;
import com.liangjinhai.supercat.shiro.ShiroHttpMethodPermissionFilter;
import com.liangjinhai.supercat.shiro.ShiroRealm;
import com.liangjinhai.supercat.shiro.ShiroSessionDao;
import com.liangjinhai.supercat.shiro.service.FilterChainDefinitionsService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.liangjinhai.supercat.common.constant.ConstantSetting.USER_SESSION_EXPIRE;


@Configuration
public class ShiroConfig {
    @Autowired
    private FilterChainDefinitionsService filterChainDefinitionsService;


    @Bean
    public ShiroFilterFactoryBean shirFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionsService.loadFilterChainDefinitionMap());
        Map map = new HashMap();
        ShiroFormAuthenticationFilter shiroFormAuthenticationFilter = new ShiroFormAuthenticationFilter();
        ShiroHttpMethodPermissionFilter httpMethodPermissionFilter = new ShiroHttpMethodPermissionFilter();
        map.put("shiroFilter", shiroFormAuthenticationFilter);
        map.put("httpMethodAnyPerms", httpMethodPermissionFilter);
        shiroFilterFactoryBean.setFilters(map);

        return shiroFilterFactoryBean;
    }

    /**
     * 自定义授权Realm
     *
     * @return
     */
    @Bean(name = "shiroRealm")
    public AuthorizingRealm getShiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(getHashedCredentialsMatcher()); //自定义加密方式
        shiroRealm.setCacheManager(getCacheManager());
        shiroRealm.setName("shiro_auth_cache");
//        shiroRealm.setAuthenticationCache(getCacheManager().getCache(shiroRealm.getName()));
        return shiroRealm;
    }

    /**
     * 实现密码验证服务
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("sid");
        cookie.setHttpOnly(true);//根据js就得不到cookie信息
        cookie.setMaxAge(-1);
        return cookie;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        // simpleCookie.setstoredCredentialsHexEncoded
        simpleCookie.setMaxAge(USER_SESSION_EXPIRE.intValue());//设置记住我保存多久
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager getRememberManager() {
        CookieRememberMeManager meManager = new CookieRememberMeManager();
        meManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        meManager.setCookie(getRememberMeCookie());
        return meManager;
    }

    public SessionDAO getSessionDAO() {
        ShiroSessionDao sessionDao = new ShiroSessionDao();
        sessionDao.setCacheManager(getCacheManager());
        sessionDao.setActiveSessionsCacheName("shiro_auth_session");
        /*EnterpriseCacheSessionDAO sessionDao = new EnterpriseCacheSessionDAO();
        sessionDao.setCacheManager(getCacheManager());
        sessionDao.setActiveSessionsCacheName("shiro_auth_session");*/
        return sessionDao;
    }

    /**
     * 安全控制器
     *
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setCacheManager(getCacheManager());
        // securityManager.setSessionManager(getSessionManager());//会话管理 通过redis  redis 序列化需要配置 om.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
        securityManager.setRememberMeManager(getRememberManager());//记住Cookie
        securityManager.setRealm(getShiroRealm());
        return securityManager;
    }

    /**
     * 缓存
     *
     * @return
     */
    @Bean(name = "springCacheManager")
    public CacheManager getCacheManager() {
        MemoryConstrainedCacheManager springCacheManager = new MemoryConstrainedCacheManager();
        return springCacheManager;
    }

    /**
     * 开启shiro注解
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(getSecurityManager());
        return advisor;
    }

    /*thymeleaf 页面要想用 shiro标签，先注入此ShiroDialect*/
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }

}