package com.liangjinhai.supercat.sys.mapper;

import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.entity.Role;
import com.liangjinhai.supercat.sys.service.UserService;
import com.liangjinhai.supercat.sys.service.RoleService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Test
    public void queryBasicUser() {
        List<User> userList = userService.queryUser();
    }


    @Test
    public void queryRoleById(){
        Role r = roleService.getRolesById(1);
        System.out.println(r.getName());
    }
    @Test
    public void getUserRole(){
//        User user = userMapper.getUserRole("test");
        User user = userService.getUserByUseName("1");
        System.out.println(user);
    }
    @Test
    @Ignore
    public void queryBasicUserById() {
//        User user = userMapper.queryUserById(1);
//        System.out.println(user);
    }
    @Test
    @Ignore
    public void getPasswordByUsername(){
//        String password = userMapper.getPasswordByUserName("test");
//        System.out.println(password);
    }

    @Test
    @Ignore
    public void insertBasicUser() {
    }

    @Test
    @Ignore
    public void updateBasicUser() {
    }

    @Test
    @Ignore
    public void deleteBasicUser() {
    }
}