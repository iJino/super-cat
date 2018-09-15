package com.liangjinhai.supercat.sys.mapper;

import com.liangjinhai.supercat.common.vo.PageResult;
import com.liangjinhai.supercat.sys.criteria.UserCriteria;
import com.liangjinhai.supercat.sys.entity.User;

import java.util.List;
/**
 * @author: liangjinhai
 * @date: 2018/7/14 13:08
 * @decapitalize: basicuser dao
 */
public interface UserMapper {
    /**
     * @author: liangjinhai
     * @date: 2018/7/14 12:49
     * @decapitalize: find all basicusers
     */
    List<User> queryUser();
    /**
     * @author: liangjinhai
     * @date: 2018/7/14 12:50
     * @decapitalize: find basicuser by id
     */
    User queryUserById(int userId);
    /**
     * @author: liangjinhai
     * @date: 2018/7/14 12:51
     * @decapitalize: insert basicuser to table
     */
    int insertUser(User user);
    /**
     * @author: liangjinhai
     * @date: 2018/7/14 12:52
     * @decapitalize: update basicuser
     */
    int updateUser(User user);
    /**
     * @author: liangjinhai
     * @date: 2018/7/14 12:54
     * @decapitalize: delete basicuser from table by id
     */
    int deleteBasicUser(int userId);
    /**
     * @author: liangjinhai
     * @date: 2018/7/20 19:57
     * @decapitalize: 根据用户名找密码
     */
    String getPasswordByUserName(String username);

    User getUserRole(String username);

    User getUserByUserName(String username);

    List<User> findAll(UserCriteria criteria);
}
