package com.liangjinhai.supercat.sys.controller;

import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("user/create");
        mv.addObject("user",new User());
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView create(User user){
        Integer result = userService.insertUser(user);
        ModelAndView mv = new ModelAndView("user/create");
        mv.addObject("user",new User());
        return mv;
    }
}
