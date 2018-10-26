package com.liangjinhai.supercat.sys.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: liangJinHai
 * @Date: 2018/10/26 14:44
 * @Description: 博客 (MongoDB)
 */
@Document(collection = "blog")
public class Blog {
    @Id
    private String id;
    /**博客内容*/
    private String blogContent;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date modifyTime;
    /**创建人*/
    private User user;
    /**浏览量*/
    private Integer pageviews;

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
