package com.liangjinhai.supercat.sys.controller;

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
        mv.addObject("name","陆家猫是一只大傻猫");
        mv.addObject("mimi","咪咪是一只大傻狗");
        return mv;
    }

}
