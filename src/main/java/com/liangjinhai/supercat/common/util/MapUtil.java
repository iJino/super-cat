package com.liangjinhai.supercat.common.util;

import java.util.Map;

public class MapUtil {
    /**
     * 转为map格式
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
