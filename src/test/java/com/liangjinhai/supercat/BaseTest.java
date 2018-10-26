package com.liangjinhai.supercat;

import com.alibaba.druid.support.json.JSONUtils;
import com.liangjinhai.supercat.sys.entity.Blog;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

public class BaseTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongo(){
        Blog blog = new Blog();
        blog.setBlogContent("哈哈哈哈哈哈");
        blog.setCreateTime(new Date());
        System.out.println(blog);
        mongoTemplate.insert(blog);
    }
}
