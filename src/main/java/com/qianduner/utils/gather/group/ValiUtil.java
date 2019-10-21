package com.qianduner.utils.gather.group;


import com.qianduner.utils.core.exception.UException;
public class ValiUtil {

    public static Object valiNull(Object object) {
        if (U.isEmpty(object))
            throw new UException("参数不能为空");
        return object;
    }

    public static boolean valiNullALL(Object... list) {
        for (Object i : list) {
            if (U.isEmpty(i))
                throw new UException("参数不能为空");
        }
        return false;
    }

    public static String valiStrLength(String string, Integer size) {
        valiNull(string);
        if (string.length() > size)
            throw new UException("参数值不符合要求");
        return string;
    }

}
