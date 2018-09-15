package com.liangjinhai.supercat.sys.criteria;

/**
 * 查询基类
 */
public class BaseCriteria {

    private Integer pageNum = 1;

    private Integer pageSize = 5;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
