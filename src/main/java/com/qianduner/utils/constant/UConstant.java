package com.qianduner.utils.constant;

import java.math.BigDecimal;

/**
 * 常量
 */
public class UConstant {

    public static boolean GATEWAY_ON = false;

    public static final Integer R_RESULT_CODE = 500;

    public static final Integer R_ERROR_CODE = -1;

    public static final String OSS_UPLOAD_PATH = "/open/";

    public static final String SYS_PRODUCT_CODE = "AA0001";

    public static final Long COMBINE_BASE_NUMBER = 10000001L;

    public static final Long DATE_SSS = 86400000L;

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static final String SYS_NULL_ERROR_MSG = "传参数据为空";

    //默认值 0
    public final static BigDecimal DEFAULT_ZORE = new BigDecimal(0);
    //默认值 1
    public final static BigDecimal DEFAULT_ONE = new BigDecimal(1);
    //默认值 2
    public final static BigDecimal DEFAULT_TWO = new BigDecimal(2);
    //默认值 2
    public final static BigDecimal DEFAULT_TEN = new BigDecimal(10);
    //服务费用
    public final static BigDecimal SERVICE_CHARGE = new BigDecimal(0.27);

    public final static BigDecimal SERVICE_BASE_CHARGE = new BigDecimal(0.01);

    public final static String SYS_ERROR_MESSAGE = "操作失败，请稍后再试";

    /** ISO-8859-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /** UTF-8 */
    public static final String UTF_8 = "UTF-8";
    /** GBK */
    public static final String GBK = "GBK";

    public final static String ADMIN_MOBILE = "15280005944";

    public final static Integer INTEGER_TEN = 10;

    public final static Integer INTEGER_ONE = 1;

    public final static Integer INTEGER_ZERO = 0;

    public final static String FORMAT_DATE_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DATE_MINUTE = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_DATE_HOUR = "yyyy-MM-dd HH";

    public static final String FORMAT_MONTH_DAY = "yyyyMMdd";

    public static final String FORMAT_DATE_DAY = "yyyy-MM-dd";

    public static final String FORMAT_DATE_MONTH = "yyyy-MM";

    public static final Long ONE_DATA_S = (1000 * 60 * 60) * 24L;

    public static final Long ONE_HOUR_S = 1000 * 60 * 60L;

    public static final String SYS_AUTHORITY_ERROR = "系统权限不足";

    public static final String COLOR_BLUE = "#01a1ff";

    public static final String COLOR_RED = "#ff3618";

    public static final String COLOR_ORAGLE = "#ffb14d";

    public static final String COLOR_GREED = "#94d94a";
    //返回加密前缀
    public static final String RESPONSE_ENCRYPT_KEY = "@Dm1nl1_0R@c1e";

    /**
     * 超级管理员ID
     */
    public static final String SUPER_ADMIN = "1";

    /**
     * 云存储配置KEY
     */
    public final static String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";

    public final static String RESOURCE_PREFIX= "/profile";

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
