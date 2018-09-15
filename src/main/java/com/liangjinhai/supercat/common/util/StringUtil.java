package com.liangjinhai.supercat.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * @Auther: liangJinHai
     * @Date: 2018/9/10 13:36
     * @Description: 将大写字母转换成下划线加小写字母 （驼峰命名转换）
     */
    public static String upperCharToUnderLine(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            System.out.println(builder.toString());
            System.out.println("mc.start():" + mc.start() + ", i: " + i);
            System.out.println("mc.end():" + mc.start() + ", i: " + i);
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }

        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }


    /**
     * @Description: 按照位数生成随机数
     * @Param: [num]
     * @return: java.lang.String
     * @Author: liangJinHai
     * @Date: 2018/9/15 12:22
     */
    public static String RandomStr(Integer num) {
        String result = "";
        for (int i = 0; i < num; i++) {
            int intVal = (int) (Math.random() * 26 + 97);
            result = result + (char) intVal;
        }
        return result;
    }
}
