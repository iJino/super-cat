package com.liangjinhai.supercat.sys.controller;

import com.liangjinhai.supercat.common.util.IpUtil;
import com.liangjinhai.supercat.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping({"/","/login"})
    public ModelAndView login(){
        ModelAndView mv =new ModelAndView("/test/test");
        return mv;
    }

    @PostMapping("/login")
    public String login( HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnknownHostException {
        User user = new User();
        user.setUsername("mark");
        user.setPassword("123456");
        boolean rememberMe = false;
        String userName = user.getUsername();
        //开始调用shiro验证
        UsernamePasswordToken token = new UsernamePasswordToken(userName, user.getPassword(), InetAddress.getLocalHost().getHostAddress().toString());
        try {
            //获取当前的subject
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + userName + "]进行登录验证..验证开始");
            if (rememberMe) {
                token.setRememberMe(true);
            }
            currentUser.login(token);
            logger.info("对用户[" + userName + "]进行登录验证..验证通过");
            SecurityUtils.getSubject().getSession().setTimeout(86400000);
            if (currentUser.isAuthenticated()) {
                logger.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                return "redirect:/index";
            }

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
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
//        loginLogService.save(new LoginLog(new Date(), "登录失败", null, userName, null, token.getHost()));
        token.clear();
        return "redirect:/login";
    }
}
