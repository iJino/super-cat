package com.liangjinhai.supercat.sys.service;

import com.liangjinhai.supercat.sys.entity.User;

import java.util.List;

public interface UserService {

    List<User> queryUser();

    User queryUserById(int userId);

    int insertUser(User user);

    int updateUser(User user);

    String getPasswordByUserName(String username);

    User getUserByUserName(String username);
}
