package com.liangjinhai.supercat.sys.controller;

import com.liangjinhai.supercat.sys.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/createBlog")
    public void createBlog(){
        Blog blog = new Blog();
        blog.setBlogContent("哈哈哈哈哈哈");
        blog.setCreateTime(new Date());
        System.out.println(blog);
        mongoTemplate.insert(blog);
    }

}
