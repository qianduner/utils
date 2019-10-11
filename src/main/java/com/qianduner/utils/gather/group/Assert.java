
package com.qianduner.utils.gather.group;

import com.qianduner.utils.core.exception.UException;

/**
 * 数据校验
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (U.isEmpty(str)) {
            throw new UException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new UException(message);
        }
    }
}
