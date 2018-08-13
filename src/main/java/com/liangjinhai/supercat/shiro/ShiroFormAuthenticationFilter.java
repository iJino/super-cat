package com.liangjinhai.supercat.shiro;

import com.liangjinhai.supercat.common.util.SpringContextUtil;
import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.service.RoleMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

       // Boolean isAccessAllowed = super.isAccessAllowed(request, response, mappedValue);
        Subject subject = getSubject(request, response);
        Boolean isAccessAllowed = (super.isAccessAllowed(request, response, mappedValue) || subject.isRemembered());//
        //如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true 证明是没登陆直接通过记住我功能进来的
        if (isAccessAllowed) {
            //获取session看看是不是空的
            Session session = subject.getSession();
            if (null != session && null == session.getAttribute("userMenus")) {//缓存菜单到session
               SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (null != simplePrincipalCollection) {
                    User user = (User) simplePrincipalCollection.getPrimaryPrincipal();
                    RoleMenuService roleMenuService = ((RoleMenuService) SpringContextUtil.getBean("roleMenuService"));
                    session.setAttribute("userMenus", roleMenuService.findRoleMenusByUserId(user));
                    session.setAttribute("userPowers", roleMenuService.findPowersByUserId(user));
                }
            }
        }
        return isAccessAllowed;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        //取身份信息
        if (null != currentUser) {
            User user = (User) currentUser.getPrincipal();
//记录登录信息
//            ((LoginLogService) SpringContextUtil.getBean("loginLogService")).save(new LoginLog(new Date(), "登录成功", user.getId(), user.getUsername(), user.getName(), IpUtil.getIpAddress((HttpServletRequest) request)));

        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, "/", null, true);
    }
}
