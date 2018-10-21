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
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("user/create");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/create")
    public ModelAndView create(User user) {
        Integer result = userService.insertUser(user);
        ModelAndView mv = new ModelAndView("user/create");
        mv.addObject("user", new User());
        return mv;
    }

    @RequestMapping("/text")
    public void text() throws ScriptException, FileNotFoundException {
        ScriptEngineManager m = new ScriptEngineManager(); //获取JavaScript执行引擎
        ScriptEngine engine = m.getEngineByName("JavaScript"); //执行JavaScript代码
        try {
            File f = new File("D:\\myProject\\super-cat-2\\src\\main\\resources\\templates\\123321.js");
            FileInputStream inputStream = new FileInputStream(f);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            System.out.println(b);

            System.out.println(new String(b));
            engine.eval(new String(b));
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
