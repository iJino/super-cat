package com.liangjinhai.supercat;

import com.liangjinhai.supercat.shiro.ShiroRealm;
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

import javax.annotation.Resource;
import javax.sql.DataSource;

public class JdbcRealmTest {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Test
    public void testAuthentication(){
        Subject currentUser = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        currentUser.login(token);

        System.out.println("isAuthenticated:"+currentUser.isAuthenticated());
        if(currentUser.isAuthenticated()){
            System.out.println(currentUser.isAuthenticated());
        }

    }
}
