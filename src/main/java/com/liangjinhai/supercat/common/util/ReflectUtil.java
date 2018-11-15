package com.liangjinhai.supercat.common.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 * @date 2018/5/18 11:10
 */
public class ReflectUtil {

    /**
     *@description(请用一句话描述本方法)
     *@param object
     *@return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String,Object> entryToMap(Object object){
        Field fields[]=object.getClass().getDeclaredFields();
        String name=null;
        Object value=null;
        Map<String,Object> map=new HashMap<>();
        try {
            Field.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                value = fields[i].get(object);//cHis 是实体类名称
                if(value==null){
                    continue;
                }
                name= fields[i].getName();
                map.put(name,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
