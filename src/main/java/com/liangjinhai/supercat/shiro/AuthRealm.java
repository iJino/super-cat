package com.liangjinhai.supercat.shiro;

import com.liangjinhai.supercat.common.util.CollectionUtil;
import com.liangjinhai.supercat.common.util.ObjectUtil;
import com.liangjinhai.supercat.sys.entity.Menu;
import com.liangjinhai.supercat.sys.entity.Role;
import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Auther: liangJinHai
 * @Date: 2018/9/7 13:03
 * @Description: shiro自定义realm
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user=(User) principals.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Menu> modules = role.getMenus();
                if(modules.size()>0) {
                    for(Menu menu : modules) {
                        permissions.add(menu.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        User user = userService.getUserRole(username);
        if (ObjectUtil.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        if (null != user.getExpiryTime() && user.getExpiryTime().getTime() < System.currentTimeMillis()) {
            throw new ExpiredCredentialsException("账号已过期");
        }
        String dbPassword= user.getPassword();
        user.setPassword("");//不将用户密码保存到session中
        return new SimpleAuthenticationInfo(user, dbPassword ,ByteSource.Util.bytes(user.getUsername() +user.getSalt()),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
}
