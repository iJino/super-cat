package com.liangjinhai.supercat.sys.enums;

/**
 * 权限枚举类
 */
public enum MenuType {
	FOLDER("文件夹"),
	URL("URL"),
	POWER("权限点");

    public final String text;

    MenuType(String text) {
        this.text = text;
    }
}