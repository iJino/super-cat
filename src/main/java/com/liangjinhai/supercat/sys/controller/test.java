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
        return "testtest";
    }

    @RequestMapping("/testFreemarker")
    public ModelAndView testFreemarker(Model model){
        ModelAndView mv = new ModelAndView("/test/test");
        Subject currentUser = SecurityUtils.getSubject();
        String name = "testShiro";
        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        currentUser.login(token);
        if(currentUser.isAuthenticated()){
            name = "login success";
        }
        mv.addObject("name",name);
        mv.addObject("mimi","咪咪是一只大傻狗");
        return mv;
    }

}
