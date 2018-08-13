package com.liangjinhai.supercat.sys.enums;

/**
 * 运价规则、用户状态枚举类
 */
public enum Status {
    DISABLED("禁用"),
    ENABLED("启用"),
    REMOVED("移除");

    public final String text;

    Status(String text) {
        this.text = text;
    }
}