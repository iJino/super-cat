package com.liangjinhai.supercat.common.util;

import java.util.Collection;

/**
 * @Auther: liangJinHai
 * @Date: 2018/7/25 10:21
 * @Description: Collection工具类
 */
public class CollectionUtil {

    /**
     * @Description: 判断Collection是否为空
     * @Param: [collection]
     * @return: boolean
     * @Author: liangJinHai
     * @Date: 2018/7/25 10:21
     */
    public static boolean isEmpty(Collection<?> collection){
        return (collection == null || collection.isEmpty());
    }
}
