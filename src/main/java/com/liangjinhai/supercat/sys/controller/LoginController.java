package com.liangjinhai.supercat.sys.controller;


import com.liangjinhai.supercat.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping({"/","/login"})
    public ModelAndView login(){
        ModelAndView mv =new ModelAndView("/login");
        return mv;
    }

    @PostMapping({"/","/login"})
    public ModelAndView login(User user,RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("/home");
        ModelAndView errormv = new ModelAndView("redirect:/login");
        String userName = user.getUsername();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            subject.getSession().setAttribute("user", user);
            return mv;
        } catch (ExpiredCredentialsException uae) {
            redirectAttributes.addFlashAttribute("message", "账号已过期");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
//            通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        usernamePasswordToken.clear();
        return errormv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, RedirectAttributes redirectAttributes) {

        try {
            logger.info("go to logout");
            SecurityUtils.getSubject().logout();
            redirectAttributes.addFlashAttribute("message", "您已安全退出");
            model.addAttribute("message", "您已安全退出");
            logger.info("登出");
        } catch (Exception e) {
            logger.error("登出异常", e);
        }
        if (null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/test/findUser";
        } else {
            return "login";
        }
    }
}
