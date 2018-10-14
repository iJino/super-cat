package com.liangjinhai.supercat.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.liangjinhai.supercat.common.util.StringUtil;
import com.liangjinhai.supercat.common.vo.PageResult;
import com.liangjinhai.supercat.sys.criteria.UserCriteria;
import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.mapper.UserMapper;
import com.liangjinhai.supercat.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
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
    public User queryUserById(int userId) {
        User user = new User();
        Cache cache = cacheManager.getCache(CACHE_KEY);
        if (null == cache.get("user_2")) {
            System.err.println("缓存里没有user_"+userId+",所以这边没有走缓存，从数据库拿数据");
            user = userMapper.queryUserById(userId);
            cache.put("user_"+userId,user);
        }else{
            user = (User) cache.get("user_"+userId).get();
        }
        return user;
    }

    @Override
    public int insertUser(User user) {
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        user.setUsername(user.getMobilePhone());
        user.setCreateTime(new Date());
        user.setCreateby(currentUser.getId());
        user.setSalt(StringUtil.RandomStr(6));
        user.setPassword(DigestUtils.md5DigestAsHex((user.getUsername() + user.getSalt() + user.getPassword()).getBytes()));
        return userMapper.insertUser(user);
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

    @Override
    public PageResult<User> findAll(UserCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
        List<User> users = userMapper.findAll(criteria);
        PageResult<User> page = new PageResult<User>(users);
        return page;
    }
}
