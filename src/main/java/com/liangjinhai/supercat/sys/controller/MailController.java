package com.liangjinhai.supercat.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    JavaMailSender jms;

    @GetMapping("/send")
    public String send(){
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("lianghinhai@foxmail.com");
        //接收者
        mainMessage.setTo("3004749981@qq.com");
        //发送的标题
        mainMessage.setSubject("heiheihei");
        //发送的内容
        mainMessage.setText("卢镇安大傻逼");
        jms.send(mainMessage);
        return "1";
    }
}
