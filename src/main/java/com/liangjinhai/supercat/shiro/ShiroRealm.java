package com.liangjinhai.supercat.shiro;

import com.liangjinhai.supercat.common.util.CollectionUtil;
import com.liangjinhai.supercat.common.util.ObjectUtil;
import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.mapper.RoleMapper;
import com.liangjinhai.supercat.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * @author: liangjinhai
     * @date: 2018/7/19 22:59
     * @decapitalize: 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        // TODO 从数据库或者缓存中获取角色数据
//        Set<String> roles = getRolesByUserName(userName);
//        // 从数据库或者缓存中获取权限数据
//        Set<String> permissions = getPermissionByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setStringPermissions(permissions);
//        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * @author: liangjinhai
     * @date: 2018/7/19 23:00
     * @decapitalize: 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //该token有包含host端口信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getHost());
        //1、从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getCredentials();
        User user = userService.getUserByUserName(userName);
        if(ObjectUtil.isEmpty(user)){
            throw new UnknownAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPasswork(), ByteSource.Util.bytes(user.getUsername() + user.getSalt()), getName());

        return authenticationInfo;
    }
}
