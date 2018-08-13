package com.liangjinhai.supercat.shiro;

import com.liangjinhai.supercat.sys.service.RoleMenuService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/2.
 */


public class ShiroSessionDao extends CachingSessionDAO {

    @Resource
    private RoleMenuService roleMenuService;


    @Override
    protected void doUpdate(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        session.removeAttribute("shiroSavedRequest");//此参数会导致退出失败
        /*if(null == session.getAttribute("userMenus") ) {//缓存菜单到session
            SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != simplePrincipalCollection) {
                user user = (user) simplePrincipalCollection.getPrimaryPrincipal();
                session.setAttribute("userMenus", ((RoleMenuService) SpringContextUtil.getBean("roleMenuService")).findRoleMenusByUserId(user));
            }
        }*/
        this.cache(session, session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        this.uncache(session);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.doUpdate(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }

        return this.getCachedSession(sessionId);

    }


}
