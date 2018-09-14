package com.liangjinhai.supercat.sys.controller;

import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.service.UserService;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class testController {

    private String USER_CACHE = "users";

    @Resource
    private UserService userService;
    @Resource
    private EhCacheCacheManager cacheManager;

    @ResponseBody
    @RequestMapping("/findUser")
    public String getUser() {
        User user = new User();
        Cache cache = cacheManager.getCache(USER_CACHE);
        user = userService.queryUserById(2);
        return user.toString();
    }

    @RequestMapping("/testEHcache")
    @ResponseBody
    public String testEHcache() {
        User user = new User();
        user = userService.queryUserById(2);
        return user.toString();
    }

}
