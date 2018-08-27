package com.liangjinhai.supercat.sys.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping({"/","/login"})
    public ModelAndView login(){
        ModelAndView mv =new ModelAndView("/login");
        return mv;
    }
}
