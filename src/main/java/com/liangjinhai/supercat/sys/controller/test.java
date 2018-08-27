package com.liangjinhai.supercat.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class test {
    @RequestMapping("/test")
    public String test(){
        return "/test/test";
    }

    @RequestMapping("/testFreemarker")
    public ModelAndView testFreemarker(Model model){
        ModelAndView mv = new ModelAndView("/test/test");
        mv.addObject("name","卢镇安");
        mv.addObject("mimi","是一只大傻狗");
        return mv;
    }

}
