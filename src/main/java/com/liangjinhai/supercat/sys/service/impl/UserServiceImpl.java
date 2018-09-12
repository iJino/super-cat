package com.liangjinhai.supercat.sys.service.impl;

import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.mapper.UserMapper;
import com.liangjinhai.supercat.sys.service.UserService;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    private static final String CACHE_KEY = "users";

    @Resource
    private UserMapper userMapper;
    @Resource
    private EhCacheCacheManager cacheManager;

    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }

    @Override
    @Cacheable(value = CACHE_KEY,key = "'user_' + #id")
    public User queryUserById(int userId) {
        User user = userMapper.queryUserById(userId);
        return user;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public String getPasswordByUserName(String username) {
        return userMapper.getPasswordByUserName(username);
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public User getUserRole(String username){
        return userMapper.getUserRole(username);
    }
}
