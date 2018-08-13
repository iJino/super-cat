package com.liangjinhai.supercat.shiro;


import com.liangjinhai.supercat.common.util.CollectionUtil;
import com.liangjinhai.supercat.common.util.ObjectUtil;
import com.liangjinhai.supercat.sys.entity.Menu;
import com.liangjinhai.supercat.sys.entity.RoleMenu;
import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.enums.MenuType;
import com.liangjinhai.supercat.sys.mapper.RoleMapper;
import com.liangjinhai.supercat.sys.service.RoleMenuService;
import com.liangjinhai.supercat.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    @Resource
    private UserService userService;
    @Resource
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        // 获取当前用户信息
        //System.out.println("..........................................................................................");
        if (user != null) {
            Set<String> roles=roleMapper.findAllByuserId(user.getId());
            SecurityUtils.getSubject().getSession().setAttribute("roles",roles);
            // 权限信息对象authorizationInfo,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
            List<RoleMenu> list = roleMenuService.findPowersByUser(user);
            List<Integer> listids = new ArrayList<>();
            list.forEach(p -> listids.add(p.getMenu().getId()));
            for (RoleMenu roleMenu : list) {
                if (roleMenu.getMenu().getParentMenu() != null && MenuType.POWER.equals(roleMenu.getMenu().getType())) {
                    Menu menu = roleMenu.getMenu().getParentMenu();
                    Boolean boo = listids.stream().anyMatch(p -> p.equals(menu.getId()));
                    if ((!boo) && MenuType.URL.equals(menu.getType()) && StringUtils.isNotEmpty(menu.getMenuKey())) {
                        authorizationInfo.addStringPermission(menu.getMenuKey());
                        listids.add(menu.getId());
                    }
                }
                if (StringUtils.isNotEmpty(roleMenu.getMenu().getMenuKey())) {
                    authorizationInfo.addStringPermission(roleMenu.getMenu().getMenuKey());
                }
            }


            return authorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.getUserByUserName(username);
        if (ObjectUtil.isEmpty(user)) {
            throw new UnknownAccountException();
        }

//        if ((!user.getAllowExtranet()) && (!token.getHost().matches("^127\\.0\\.([0-9]{1,3})\\.([0-9]{1,3})$")) && (!"0:0:0:0:0:0:0:1".equals(token.getHost()))) {
//            throw new UnknownAccountException("抱歉，不允许通过外网登录");
//        }
        if (null != user.getExpiryTime() && user.getExpiryTime().getTime() < new Date().getTime()) {
            throw new ExpiredCredentialsException("账号已过期");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,//用户名
                user.getPassword(),//密码
                ByteSource.Util.bytes(user.getUsername() + user.getSalt()),
                getName()//realm name

        );
        return authenticationInfo;
    }


}