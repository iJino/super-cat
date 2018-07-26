package com.liangjinhai.supercat.common.util;


import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtil {

    public static boolean isEmpty(Object obj){
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        return false;
    }
}
