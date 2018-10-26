package com.liangjinhai.supercat.sys.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: LiangJinHai
 * @Date: 2018/10/8
 * @Description: 字典
 */
@Document(collection = "dictionary_data")
public class DictionaryData {
    /**
     *主键
     */
    @Id
    private Integer id;
    /**
     *代码
     */
    private String code;
    /**
     *数据
     */
    private String data;
    /**
     *简码
     */
    private String shortCode;
    /**
     *类别
     */
    private String type;
    /**
     *状态
     */
    private String status;
    /**
     *备注
     */
    private String remark;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *修改时间
     */
    private Date modifyTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
