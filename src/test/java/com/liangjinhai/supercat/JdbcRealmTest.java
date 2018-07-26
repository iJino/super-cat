package com.liangjinhai.supercat;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

public class JdbcRealmTest {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Test
    public void testAuthentication(){

       JdbcRealm jdbcRealm = new JdbcRealm();
       jdbcRealm.setDataSource(dataSource);


        //1、创建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        subject.login(token);

        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        subject.isAuthenticated();

//        subject.logout();
//
//        System.out.println("isAuthenticated:"+subject.isAuthenticated());
//        subject.checkRoles("admin");
//
//        subject.checkPermission("user:update");
    }
}
