package com.liangjinhai.supercat.sys.enums;

public enum HttpMethod {
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    POST("POST"),
    PUT("PUT"),
    TRACE("TRACE");

    public final String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
