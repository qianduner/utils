package com.qianduner.utils.gather.group;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * uuid工具类
 */
public class UUIDUtil {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "po", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
            "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    /**
     * 生成短8位uuid 可能重复
     * @return UUID短8位
     */
    public static String generateShort8Uuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    /**
     * 生成短32位uuid
     * @return String 生成短32位uuid
     */
    public static String generate32Uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取订单号或流水号
     * @return 流水号
     */
    public static String getTradeNo() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String before = df.format(new Date());

        Random random = new Random();
        StringBuilder after = new StringBuilder(11);
        int a1[] = new int[11];
        for (int i = 0; i < a1.length; i++) {
            //生成一个介于0到9的数字
            a1[i] = random.nextInt(10);
            for (int j = 1; j < i; j++) {
                //如果重复，退回去重新生成随机数
                while (a1[i] == a1[j]) {
                    if (i == 0) {
                        break;
                    }
                    i--;
                }
            }
        }
        for (int i = 0; i < a1.length; i++) {
            after.append(a1[i]);
        }

        return before + after.toString();
    }

}



