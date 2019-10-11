package com.qianduner.utils.gather.group;

import com.qianduner.utils.core.exception.UException;
import com.qianduner.utils.typewrap.Dto;
import com.qianduner.utils.typewrap.utils.TypeConvertUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.*;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <b>辅助工具类</b>
 *
 * @author Laver
 * @date 2016-1-22
 * @since 1.0
 */
public class U {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date format(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 　　* 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     * 　　* @param sourceDate
     * 　　* @param formatLength
     * 　　* @return 重组后的数据
     */

    public static String replenishZore(int sourceDate, int formatLength) {
        /*
         * 0 指前面补充零
         * formatLength 字符总长度为 formatLength
         * d 代表为正数。
         */
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }


    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if (pObj == "")
            return true;
        if ("".equals(pObj))
            return true;

        isFlag(pObj);
        return false;
    }

    public static String hideMobile(String mobile) {
        if (isEmpty(mobile))
            return mobile;
        return mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4, mobile.length());
    }


    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObjs 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object... pObjs) {
        for (Object pObj : pObjs) {
            if (pObj == null)
                return true;
            if (pObj == "")
                return true;
            isFlag(pObj);
        }
        return false;
    }

    /**
     * 判断日期是否是当天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;
    }


    /**
     * 数字千位分隔符
     *
     * @param value
     * @return
     */
    public static String kilobit(BigDecimal value) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
        return df.format(value);
    }

    /**
     * 数字千位分隔符
     *
     * @param value
     * @return
     */
    public static String kilobitDecimals(BigDecimal value) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###.000000");
        return df.format(value);
    }

    public static boolean isFlag(Object pObj) {
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 数据转成负数
     *
     * @param decimal
     * @return
     */
    public static BigDecimal fanZhuan(BigDecimal decimal) {
        decimal = decimal.multiply(new BigDecimal(-1));
        return decimal;
    }


    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null)
            return false;
        if (pObj == "")
            return false;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Java对象之间属性值拷贝(Dto、Map、JavaBean)
     *
     * @param pFromObj Bean源对象
     * @param pToObj   Bean目标对象
     */
    public static void copyProperties(Object pFromObj, Object pToObj) {
        if (pToObj != null) {
            try {
                // 支持属性空值复制
                BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
                // 日期类型复制
                BeanUtilsDateConverter converter = new BeanUtilsDateConverter();
                ConvertUtils.register(converter, Date.class);
                ConvertUtils.register(converter, java.sql.Date.class);
                BeanUtils.copyProperties(pToObj, pFromObj);
            } catch (Exception e) {
                throw new UException("JavaBean之间的属性值拷贝发生错误", e);
            }
        }
    }

    /**
     * 将JavaBean对象中的属性值拷贝到Dto对象
     *
     * @param pFromObj JavaBean对象源
     * @param pToDto   Dto目标对象
     */
    public static void copyProperties(Object pFromObj, Dto pToDto) {
        if (pToDto != null) {
            try {
                pToDto.putAll(BeanUtils.describe(pFromObj));
                pToDto.remove("class");
            } catch (Exception e) {
                throw new UException("将JavaBean属性值拷贝到Dto对象发生错误", e);
            }
        }
    }

    /**
     * 将传入的身份证号码进行校验，并返回一个对应的18位身份证
     *
     * @param personIDCode 身份证号码
     * @return String 十八位身份证号码
     */
    public static String getFixedPersonIDCode(String personIDCode) {
        if (personIDCode == null)
            throw new UException("输入的身份证号无效");

        if (personIDCode.length() == 18) {
            if (isIdentity(personIDCode))
                return personIDCode;
            else
                throw new UException("输入的身份证号无效");
        } else if (personIDCode.length() == 15)
            return fixPersonIDCodeWithCheck(personIDCode);
        else
            throw new UException("输入的身份证号无效");
    }

    /**
     * 修补15位居民身份证号码为18位，并校验15位身份证有效性
     *
     * @param personIDCode 十五位身份证号码
     * @return String 十八位身份证号码
     */
    public static String fixPersonIDCodeWithCheck(String personIDCode) {
        if (personIDCode == null || personIDCode.trim().length() != 15)
            throw new UException("输入的身份证号不足15位");

        if (!isIdentity(personIDCode))
            throw new UException("输入的身份证号无效");

        return fixPersonIDCodeWithoutCheck(personIDCode);
    }

    /**
     * 修补15位居民身份证号码为18位，不校验身份证有效性
     *
     * @param personIDCode 十五位身份证号码
     * @return 十八位身份证号码
     */
    public static String fixPersonIDCodeWithoutCheck(String personIDCode) {
        String id17 = null;
        if (personIDCode != null && personIDCode.length() == 17)
            id17 = personIDCode;
        else if (personIDCode != null || personIDCode.trim().length() == 15)
            id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15); // 15位身份证补'19'
        else
            throw new UException("输入的身份证号不足15位");

        char[] code = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; // 11个校验码字符
        int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1}; // 18个加权因子
        int[] idcd = new int[18];
        int sum; // 根据公式 ∑(ai×Wi) 计算
        int remainder; // 第18位校验码
        for (int i = 0; i < 17; i++) {
            idcd[i] = Integer.parseInt(id17.substring(i, i + 1));
        }
        sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + idcd[i] * factor[i];
        }
        remainder = sum % 11;
        String lastCheckBit = String.valueOf(code[remainder]);
        return id17 + lastCheckBit;
    }

    /**
     * 判断是否是有效的18位或15位居民身份证号码
     *
     * @param identity 18位或15位居民身份证号码
     * @return 是否为有效的身份证号码
     */
    public static boolean isIdentity(String identity) {
        if (identity == null)
            return false;
        if (identity.length() == 18 || identity.length() == 15) {
            String id15 = null;
            String id17 = null;
            if (identity.length() == 18) {
                id17 = identity.substring(0, 17);
                id15 = identity.substring(0, 6) + identity.substring(8, 17);
            } else
                id15 = identity;
            try {
                Long.parseLong(id15); // 校验是否为数字字符串

                String birthday = "19" + id15.substring(6, 12);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                sdf.parse(birthday); // 校验出生日期
                if (identity.length() == 18 && !fixPersonIDCodeWithoutCheck(id17).equals(identity))
                    return false; // 校验18位身份证
            } catch (Exception e) {
                return false;
            }
            return true;
        } else
            return false;
    }

    /**
     * 从身份证号中获取出生日期，身份证号可以为15位或18位
     *
     * @param identity 身份证号
     * @return 出生日期
     * 报错身份证号出生日期段有误
     */
    public static Timestamp getBirthdayFromPersonIDCode(String identity) {
        String id = getFixedPersonIDCode(identity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Timestamp birthday = new Timestamp(sdf.parse(id.substring(6, 14)).getTime());
            return birthday;
        } catch (ParseException e) {
            throw new UException("不是有效的身份证号", e);
        }
    }

    /**
     * 从身份证号获取性别
     *
     * @param identity 身份证号
     * @return 性别代码
     * @throws Exception 无效的身份证号码
     */
    public static String getGenderFromPersonIDCode(String identity) throws Exception {
        String id = getFixedPersonIDCode(identity);
        char sex = id.charAt(16);
        return sex % 2 == 0 ? "2" : "1";
    }

    private static String DigitUppercaseStrings[] = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private static String moneyStrings[] = new String[]{"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟",
            "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};

    /**
     * 将货币转换为大写形式(类内部调用)
     *
     * @param NumStr
     * @return String
     */
    private static String PositiveIntegerToHanStr(String NumStr) {
        // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = NumStr.length();
        if (len > 15)
            return "数值过大!";
        for (int i = len - 1; i >= 0; i--) {
            if (NumStr.charAt(len - i - 1) == ' ')
                continue;
            n = NumStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9)
                return "输入含非数字字符!";

            if (n != 0) {
                if (lastzero)
                    RMBStr += DigitUppercaseStrings[0]; // 若干零后若跟非零值，只显示一个零
                // 除了亿万前的零不带到后面
                // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )
                // 如十进位前有零也不发壹音用此行
                if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
                    RMBStr += DigitUppercaseStrings[n];
                RMBStr += moneyStrings[i]; // 非零值后加进位，个位为空
                hasvalue = true; // 置万进位前有值标记

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
                    RMBStr += moneyStrings[i]; // “亿”或“万”
            }
            if (i % 8 == 0)
                hasvalue = false; // 万进位前有值标记逢亿复位
            lastzero = (n == 0) && (i % 4 != 0);
        }

        if (RMBStr.length() == 0)
            return DigitUppercaseStrings[0]; // 输入空字符或"0"，返回"零"
        return RMBStr;
    }

    /**
     * 将货币转换为大写形式
     *
     * @param val 传入的数据
     * @return String 返回的人民币大写形式字符串
     */
    public static String numToRMBStr(double val) {
        String SignStr = "";
        String TailStr = "";
        long fraction, integer;
        int jiao, fen;

        if (val < 0) {
            val = -val;
            SignStr = "负";
        }
        if (val > 99999999999999.999 || val < -99999999999999.999)
            return "数值位数过大!";
        // 四舍五入到分
        long temp = Math.round(val * 100);
        integer = temp / 100;
        fraction = temp % 100;
        jiao = (int) fraction / 10;
        fen = (int) fraction % 10;
        if (jiao == 0 && fen == 0) {
            TailStr = "整";
        } else {
            TailStr = DigitUppercaseStrings[jiao];
            if (jiao != 0)
                TailStr += "角";
            // 零元后不写零几分
            if (integer == 0 && jiao == 0)
                TailStr = "";
            if (fen != 0)
                TailStr += DigitUppercaseStrings[fen] + "分";
        }
        // 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
        // if( !integer ) return SignStr+TailStr;
        return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
    }

    /**
     * 获取指定年份和月份对应的天数
     *
     * @param year  指定的年份
     * @param month 指定的月份
     * @return int 返回天数
     */
    public static int getDaysInMonth(int year, int month) {
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10)
                || (month == 12)) {
            return 31;
        } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            return 30;
        } else {
            if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {
                return 29;
            } else {
                return 28;
            }
        }
    }

    /**
     * 根据所给的起止时间来计算间隔的天数
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return int 返回间隔天数
     */
    public static int getIntervalDays(Date startDate, Date endDate) {
        long startdate = startDate.getTime();
        long enddate = endDate.getTime();
        long interval = enddate - startdate;
        int intervalday = (int) (interval / (1000 * 60 * 60 * 24));
        return intervalday;
    }

    /**
     * 根据所给的起止时间来计算间隔的月数
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return int 返回间隔月数
     */
    public static int getIntervalMonths(java.sql.Date startDate, java.sql.Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int startDateM = startCal.MONTH;
        int startDateY = startCal.YEAR;
        int enddatem = endCal.MONTH;
        int enddatey = endCal.YEAR;
        int interval = (enddatey * 12 + enddatem) - (startDateY * 12 + startDateM);
        return interval;
    }

    /**
     * 返回指定格式的当前日期时间字符串
     *
     * @param format
     * @return
     */
    public static String getDateTimeStr(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    /**
     * 返回指定格式的当前日期时间字符串
     *
     * @param format
     * @return
     */
    public static String getDateAddTimeStr(String format, int amount) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = dateAdd(new Date(), Calendar.DATE, amount);
        returnStr = f.format(date);
        return returnStr;
    }

    public static Long getDateTimeLong(Date date, String format) throws ParseException {

        SimpleDateFormat f = new SimpleDateFormat(format);
        if (isEmpty(date))
            date = new Date();
        if (isEmpty(format))
            format = DATE_PATTERN;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(f.format(date)).getTime();
    }

    /**
     * 返回缺省格式的当前日期时间字符串 默认格式:yyyy-mm-dd hh:mm:ss
     *
     * @return String
     */
    public static String getDateTimeStr() {
        return getDateTimeStr(DATE_TIME_PATTERN);
    }

    /**
     * 返回自定义格式的当前日期时间字符串
     *
     * @param format 格式规则
     * @return String 返回当前字符串型日期时间
     */
    public static String getDateStr(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    /**
     * 返回当前日期字符串 缺省格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getDateStr() {
        return getDateStr(DATE_PATTERN);
    }

    /**
     * 返回当前日期Date对象
     */
    public static Date getDate() {
        Object obj = TypeConvertUtil.convert(getDateStr(), "Date", DATE_PATTERN);
        if (obj != null)
            return (Date) obj;
        else
            return null;
    }

    /**
     * 返回当前日期Timestamp对象
     */
    public static Timestamp getDateTime() {
        Object obj = TypeConvertUtil.convert(getDateTimeStr(), "Timestamp", DATE_TIME_PATTERN);
        if (obj != null)
            return (Timestamp) obj;
        else
            return null;
    }

    /**
     * 返回指定格式的字符型日期
     *
     * @param date
     * @param formatString
     * @return
     */
    public static String date2String(Date date, String formatString) {
        if (isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpledateformat = new SimpleDateFormat(formatString);
        String strDate = simpledateformat.format(date);
        return strDate;
    }

    /**
     * 日期加减
     *
     * @param dt
     * @param calendarType
     * @param amount
     * @return
     */
    public static Date dateAdd(Date dt, int calendarType, int amount) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);
        cl.add(calendarType, amount);
        return cl.getTime();
    }

    public static Date dateHour(int date, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date hourAddSub(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        System.out.println(sdf.format(calendar.getTime()));
        calendar.add(Calendar.HOUR_OF_DAY, amount);//1小时前
        System.out.println(sdf.format(calendar.getTime()));
        return calendar.getTime();
    }

    /**
     * 将字符串型日期转换为日期型
     *
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate) {
        Date tmpDate = (new SimpleDateFormat(YmCons.DATATIME)).parse(strDate, new ParsePosition(0));
        if (tmpDate == null) {
            tmpDate = (new SimpleDateFormat(YmCons.DATA)).parse(strDate, new ParsePosition(0));
        }
        return tmpDate;
    }

    /**
     * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性<br>
     *
     * @param agent request.getHeader("USER-AGENT");
     */
    public static String encodeChineseDownloadFileName(String agent, String pFileName) {
        try {
            if (null != agent && -1 != agent.indexOf("MSIE")) {
                pFileName = URLEncoder.encode(pFileName, "utf-8");
            } else {
                pFileName = new String(pFileName.getBytes("utf-8"), "iso8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pFileName;
    }

    /**
     * 根据日期获取星期
     *
     * @param strdate
     * @return
     */
    public static String getWeekDayByDate(String strdate) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        SimpleDateFormat sdfInput = new SimpleDateFormat(DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdfInput.parse(strdate);
        } catch (ParseException e) {
            throw new UException("日期类型转换出错", e);
        }
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek < 0)
            dayOfWeek = 0;
        return dayNames[dayOfWeek];
    }

    /**
     * 区分今天昨天前天
     *
     * @param createTime
     * @return
     */
    public static String parseNewDate(String createTime) {
        try {
            String ret = "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
            long create = sdf.parse(createTime).getTime();
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now
                    .get(Calendar.SECOND));// 毫秒数
            long ms_now = now.getTimeInMillis();
            if (ms_now - create < ms) {
                ret = "今天";
            } else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
                ret = "昨天";
            } else if (ms_now - create < (ms + 24 * 3600 * 1000 * 2)) {
                ret = "前天";
            } else {
                ret = "更早";
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JS输出含有\n的特殊处理
     *
     * @param pStr
     * @return
     */
    public static String replace4JsOutput(String pStr) {
        pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
        pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        pStr = pStr.replace(" ", "&nbsp;");
        return pStr;
    }

    /**
     * 获取class文件所在绝对路径
     *
     * @param cls
     * @return
     * @throws IOException
     */
    public static String getPathFromClass(Class cls) {
        String path = null;
        if (cls == null) {
            throw new NullPointerException();
        }
        URL url = getClassLocationURL(cls);
        if (url != null) {
            path = url.getPath();
            if ("jar".equalsIgnoreCase(url.getProtocol())) {
                try {
                    path = new URL(path).getPath();
                } catch (MalformedURLException e) {
                }
                int location = path.indexOf("!/");
                if (location != -1) {
                    path = path.substring(0, location);
                }
            }
            File file = new File(path);
            try {
                path = file.getCanonicalPath();
            } catch (IOException e) {
                throw new UException("获取class文件所在绝对路径出错", e);
            }
        }
        return path;
    }

    /**
     * 这个方法可以通过与某个类的class文件的相对路径来获取文件或目录的绝对路径。 通常在程序中很难定位某个相对路径，特别是在B/S应用中。
     * 通过这个方法，我们可以根据我们程序自身的类文件的位置来定位某个相对路径。
     * 比如：某个txt文件相对于程序的Test类文件的路径是../../resource/test.txt，
     * 那么使用本方法Path.getFullPathRelateClass("../../resource/test.txt",Test.class)
     * 得到的结果是txt文件的在系统中的绝对路径。
     *
     * @param relatedPath 相对路径
     * @param cls         用来定位的类
     * @return 相对路径所对应的绝对路径
     * @throws IOException 因为本方法将查询文件系统，所以可能抛出IO异常
     */
    public static String getFullPathRelateClass(String relatedPath, Class cls) {
        String path = null;
        if (relatedPath == null) {
            throw new NullPointerException();
        }
        String clsPath = getPathFromClass(cls);
        File clsFile = new File(clsPath);
        String tempPath = clsFile.getParent() + File.separator + relatedPath;
        File file = new File(tempPath);
        try {
            path = file.getCanonicalPath();
        } catch (IOException e) {
            throw new UException("获取class文件所在绝对路径出错", e);
        }
        return path;
    }

    /**
     * 获取类的class文件位置的URL
     *
     * @param cls
     * @return
     */
    private static URL getClassLocationURL(final Class cls) {
        if (cls == null)
            throw new IllegalArgumentException("null input: cls");
        URL result = null;
        final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
        final ProtectionDomain pd = cls.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null)
                result = cs.getLocation();
            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip"))
                            result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
                        else if (new File(result.getFile()).isDirectory())
                            result = new URL(result, clsAsResource);
                    } catch (MalformedURLException ignore) {
                    }
                }
            }
        }
        if (result == null) {
            final ClassLoader clsLoader = cls.getClassLoader();
            result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader
                    .getSystemResource(clsAsResource);
        }
        return result;
    }

    /**
     * 字符串编码转换工具类
     *
     * @param pString
     * @return
     */
    public static String getGBK(String pString) {
        if (isEmpty(pString)) {
            return "";
        }
        try {
            pString = new String(pString.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new UException("不支持的字符集编码", e);
        }
        return pString;
    }

    /**
     * 检查当前ClassLoader种,是否存在指定class
     *
     * @param pClass 类路径
     * @return
     */
    public static boolean isExistClass(String pClass) {
        try {
            Class.forName(pClass);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }


    /**
     * 判断是否是IE浏览器
     *
     * @param userAgent request.getHeader("USER-AGENT")
     * @return
     */
    public static boolean isIE(String userAgent) {
        userAgent = userAgent.toLowerCase();
        return StringUtils.contains(userAgent, "msie");
    }

    /**
     * 获取IE版本号
     *
     * @param userAgent request.getHeader("USER-AGENT")
     * @return
     */
    public static String getIeVersion(String userAgent) {
        String ieVersion = "";
        if (isIE(userAgent)) {
            userAgent = U.trimAll(userAgent);
            userAgent = StringUtils.lowerCase(userAgent);
            if (StringUtils.contains(userAgent, "rv:")) {
                // IE11: Mozilla/5.0 (MSIE 9.0; qdesk 2.5.1277.202; Windows NT
                // 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko
                ieVersion = StringUtils.substringBetween(userAgent, "rv:", ".");
            } else {
                // IE6-10: Mozilla / 5.0(compatible; MSIE 6.0-10.0; Windows NT
                // 6.2; Trident / 6.0)
                ieVersion = StringUtils.substringBetween(userAgent, "msie", ".");
            }
        }
        return ieVersion;
    }

    /**
     * 判断是否是Chrome浏览器
     *
     * @param userAgent request.getHeader("USER-AGENT")
     * @return
     */
    public static boolean isChrome(String userAgent) {
        userAgent = userAgent.toLowerCase();
        return StringUtils.contains(userAgent, "chrome");
    }

    /**
     * 判断是否是Firefox浏览器
     *
     * @param userAgent request.getHeader("USER-AGENT")
     * @return
     */
    public static boolean isFirefox(String userAgent) {
        userAgent = userAgent.toLowerCase();
        return StringUtils.contains(userAgent, "firefox");
    }

    /**
     * 替换空字符串，原生trim只能替换字符串前后
     *
     * @param aString
     * @return
     */
    public static String trimAll(String aString) {
        if (U.isEmpty(aString)) {
            return aString;
        }
        return aString.replaceAll(" ", "");
    }

    /**
     * 打印调试对象
     */
    public static void debug(Object object) {
        System.out.println(object);
    }

    /**
     * 产生[0-9]之间的随机数
     *
     * @return
     */
    public static int random() {
        return (int) (Math.random() * 10);
    }

    /**
     * 产生指定范围[min-max]之间的随机数
     *
     * @return
     */
    public static long randomBetween(long min, long max) {
        return Math.round(Math.random() * (max - min) + min);
    }

    /**
     * 产生指定范围[min-max]之间的随机数
     *
     * @return
     */
    public static String randomBetweenStr(long min, long max) {
        return "" + Math.round(Math.random() * (max - min) + min);
    }

    /**
     * 产生随机简体汉字
     *
     * @return
     */
    public static String randomSimplified() {
        String outCharacter = null;
        Integer hightPos, lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (hightPos.byteValue());
        b[1] = (lowPos.byteValue());
        try {
            outCharacter = new String(b, "GBK");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return outCharacter;
    }


    /**
     * 获取主机名
     *
     * @return
     */
    public static String getHostName() {
        String hostName = null;
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    /**
     * 获取主机IP
     *
     * @return
     */
    public static String getHostAddress() {
        String hostAddress = null;
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            hostAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostAddress;
    }

    static boolean flag = false;
    static String regex = "";

    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "^\\w+[-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$ ";
        return check(email, regex);
    }

    /**
     * 验证手机号码
     * <p>
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
        return check(cellphone, regex);
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(telephone, regex);
    }

    /**
     * 验证传真号码
     *
     * @param fax
     * @return
     */
    public static boolean checkFax(String fax) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(fax, regex);
    }


    /**
     * 将url参数转换成map
     *
     * @param param aa=11&bb=22&cc=33
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
     * emoji表情替换
     *
     * @param source  原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source, String slipStr) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        } else {
            return source;
        }
    }


    public static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    public static Date getLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    /**
     * <p>转为unicode 编码<p>
     *
     * @param str
     * @return unicodeString
     */
    public static String utf8ToUnicode(String str) {
        String prefix = "\\u";
        StringBuffer sb = new StringBuffer();
        char[] chars = str.toCharArray();
        if (chars == null || chars.length == 0) {
            return null;
        }
        for (char c : chars) {
            sb.append(prefix);
            sb.append(Integer.toHexString(c));
        }
        return sb.toString();
    }

    /**
     * 把unicode编码转换为中文
     *
     * @param str
     * @return
     */
    public static String unicodeToUtf8(String str) {
        String sg = "\\u";
        int a = 0;
        List<String> list = new ArrayList<>();
        while (str.contains(sg)) {
            str = str.substring(2);
            String substring;
            if (str.contains(sg)) {
                substring = str.substring(0, str.indexOf(sg));
            } else {
                substring = str;
            }
            if (str.contains(sg)) {
                str = str.substring(str.indexOf(sg));
            }
            list.add(substring);
        }
        StringBuffer sb = new StringBuffer();
        if (!CollectionUtils.isEmpty(list)) {
            for (String string : list) {
                sb.append((char) Integer.parseInt(string, 16));
            }
        }
        return sb.toString();
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 测试
     *
     * @param args
     */
    //public static void main(String args[]) {
    //    //System.out.println(YmCodec.md5("回收账户"));
    //    //System.out.println(getByte16Wallet("回收账户"));
    //    //String string = "\\xF0\\x9F\\x94\\x92�都嗨��、齐静��给你��";
    //    //System.out.println(containsEmoji(string));
    //    //System.out.println(filterEmoji(string));
    //    System.out.println(U.getDate().getTime());
    //    //try {
    //    //} catch (ParseException e) {
    //    //    e.printStackTrace();
    //    //}
    //    //System.out.println(getUrlParams("p=oWvPX3cA==O78C/jqHDyAVkj&s=386144364065314136237e633935632a6f31353137313235373437393030&v=64b9a53d5d3f25c59f5686e0db27d207&m=U2FsdGVkX18+tgZD0NGA61ZTBihKl0/Mpys8O0wku+0=\n"));
    //    //System.out.println(YmCodec.decryptDES("U2FsdGVkX19W3a24TfL9Z3uhF6Rq+9rz76wIyonU5Y4=", "9y4pHKVFDsON7AQ=="));
    //    //System.out.println(hideMobile("13588444801230"));
    //    //System.out.println(isToday(U.dateAdd(U.getDateTime(),Calendar.DATE,-1)));
    //}
}