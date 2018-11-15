package com.liangjinhai.supercat.sys.controller;


import com.liangjinhai.supercat.sys.entity.User;
import com.liangjinhai.supercat.sys.service.UserService;
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


import javax.annotation.Resource;

@Controller
public class LoginController {
    @Resource
    private UserService userService;

    private final static  Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping({"/","/login"})
    public ModelAndView login(){
        ModelAndView mv =new ModelAndView("/index");
        ModelAndView success = new ModelAndView("/home");
        try {
            //    若已经登录或者cookies还存在
            if (null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) {
                log.info("--进行登录验证..验证开始");
                return success;
            } else {
                return mv;
            }
        } catch (Exception e) {

        }
        return mv;
    }

    @PostMapping({"/","/login"})
    public ModelAndView login(User user,Boolean rememberMe,RedirectAttributes redirectAttributes, Model model){
        ModelAndView successMV = new ModelAndView("/home");
        ModelAndView errorMV = new ModelAndView("redirect:/index");
        String userName = user.getUsername();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            if (null != rememberMe && rememberMe) {
                usernamePasswordToken.setRememberMe(true);
            }
            subject.login(usernamePasswordToken);   //完成登录
            subject.getSession().setAttribute("user", userService.getPasswordByUserName(user.getUsername()));
            model.addAttribute("user",(User)SecurityUtils.getSubject().getPrincipal());
            return successMV;
        } catch (ExpiredCredentialsException uae) {
            errorMV.addObject("message", "账号已过期");
        } catch (UnknownAccountException uae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            errorMV.addObject("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            errorMV.addObject("message", "用户名或密码不正确");
        } catch (LockedAccountException lae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
            errorMV.addObject("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
            errorMV.addObject("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
//            通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            errorMV.addObject("message", "用户名或密码不正确");
        }
        usernamePasswordToken.clear();
        return errorMV;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, RedirectAttributes redirectAttributes) {

        try {
            log.info("go to logout");
            SecurityUtils.getSubject().logout();
            redirectAttributes.addFlashAttribute("message", "您已安全退出");
            model.addAttribute("message", "您已安全退出");
            log.info("登出");
        } catch (Exception e) {
            log.error("登出异常", e);
        }
        if (null != SecurityUtils.getSubject() && SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/home";
        } else {
            return "login";
        }
    }
}
