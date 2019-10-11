package com.qianduner.utils.gather.codec.base64;

import com.qianduner.utils.gather.group.U;

import java.util.Calendar;
import java.util.Date;

public class RandomUtil {
    public static String getCompute(String content, Integer number) {
        char[] chars = content.toCharArray();
        double value = 0;
        for (int i = 0; i < chars.length; i++) {
            value = value * (int) chars[i] * 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679;
            value = value % number;
            value = value + 1;
            if (value > number)
                value = number;
        }
        return U.replenishZore((int) value, 2);
    }

    public static Date typeGetOpenDateTime(String type, Date date) {
        Calendar cal = Calendar.getInstance();
        if ("0".equals(type)) {
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.MILLISECOND, 0);
            if (U.isEmpty(date))
                date = cal.getTime();
            date = U.dateAdd(date, Calendar.MINUTE, 10);
        } else if ("1".equals(type)) {
            if (U.isEmpty(date)) {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                date = cal.getTime();
            }
            date = U.dateAdd(date, Calendar.HOUR, 1);
        } else if ("2".equals(type)) {
            if (U.isEmpty(date)) {
                cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 20);
                cal.set(Calendar.MINUTE, 15);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                date = cal.getTime();
            }
            date = U.dateAdd(date, Calendar.DATE, 1);
        } else if ("3".equals(type)) {
            if (U.isEmpty(date)) {
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                cal.set(Calendar.HOUR_OF_DAY, 20);
                cal.set(Calendar.MINUTE, 30);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                date = cal.getTime();
            }  else {
                date = U.dateAdd(date, Calendar.DATE, 7);
            }
        } else if ("4".equals(type)) {
            if (U.isEmpty(date)) {
                Calendar instance = Calendar.getInstance();
                instance.set(Calendar.DAY_OF_MONTH, 1);
                instance.set(Calendar.HOUR_OF_DAY, 21);
                instance.set(Calendar.SECOND, 0);
                instance.set(Calendar.MINUTE, 0);
                instance.set(Calendar.MILLISECOND, 0);
                date = instance.getTime();
            } else {
                date = U.dateAdd(date, Calendar.MONDAY, 1);
            }

        } else {
            return null;
        }
        return date;
    }

}
