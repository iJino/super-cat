package com.liangjinhai.supercat.sys.mapper;

import com.liangjinhai.supercat.sys.criteria.UserCriteria;
import com.liangjinhai.supercat.sys.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
/**
 * @author: liangjinhai
 * @date: 2018/7/14 13:08
 * @decapitalize: basicuser dao
 */
public interface UserMapper {

    List<User> queryUser();

    User queryUserById(int userId);

    int insertUser(User user);

    int updateUser(User user);

    int deleteBasicUser(int userId);

    String getPasswordByUserName(String username);

    User getUserRole(String username);

    User getUserByUserName(String username);

    List<User> findAll(UserCriteria criteria);
}
