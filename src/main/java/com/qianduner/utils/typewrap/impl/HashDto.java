package com.qianduner.utils.typewrap.impl;

import com.alibaba.fastjson.JSON;
import com.qianduner.utils.constant.UConstant;
import com.qianduner.utils.gather.group.U;
import com.qianduner.utils.typewrap.Dto;
import com.qianduner.utils.typewrap.utils.TypeConvertUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


/**
 * <b>数据传输对象实现</b>
 * <p>
 * 对原生Java Map类型的二次包装，提供<b><i>更加方便的存取API、更强的容错和类型转换机制。</i></b>
 * 在平台二次开发过程中具有很强的实用价值。 开发人员需熟练掌握其提供的相关API。
 * </p>
 */
public class HashDto extends HashMap<String, Object> implements Dto {

    private static final long serialVersionUID = 1L;

    /**
     * 缺省构造函数
     */
    public HashDto() {

    }

    /**
     * 以Integer类型返回属性
     *
     * @param pKey 键名
     * @return Integer 键值
     */

    public Integer getInteger(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "Integer", null);
        if (obj != null)
            return (Integer) obj;
        else
            return null;
    }

    /**
     * 以BigInteger类型返回属性
     *
     * @param pKey 键名
     * @return BigInteger 键值
     */
    public BigInteger getBigInteger(String pKey) {
        BigInteger outValue = null;
        Object obj = get(pKey);
        if (obj instanceof BigInteger) {
            outValue = (BigInteger) obj;
        } else {
            outValue = new BigInteger(getString(pKey));
        }
        return outValue;
    }

    /**
     * 以Long类型返回属性
     *
     * @param pKey 键名
     * @return Long 键值
     */

    public Long getLong(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "Long", null);
        if (obj != null)
            return (Long) obj;
        else
            return null;
    }

    /**
     * 以String类型返回属性
     *
     * @param pKey 键名
     * @return String 键值
     */

    public String getString(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "String", null);
        if (obj != null)
            return (String) obj;
        else
            return "";
    }

    /**
     * 以BigDecimal类型返回属性
     *
     * @param pKey 键名
     * @return BigDecimal 键值
     */

    public BigDecimal getBigDecimal(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "BigDecimal", null);
        if (obj != null)
            return (BigDecimal) obj;
        else
            return null;
    }

    /**
     * 以BigDecimal类型返回属性
     *
     * @param pKey 键名
     * @return BigDecimal 键值
     */

    public Double getDouble(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "Double", null);
        if (obj != null)
            return (Double) obj;
        else
            return null;
    }

    /**
     * 以Date类型返回属性
     *
     * @param pKey 键名
     * @return Date 键值(yyyy-MM-dd)
     */

    public Date getDate(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "Date", "yyyy-MM-dd");
        if (obj != null)
            return (Date) obj;
        else
            return null;
    }

    /**
     * 以Timestamp类型返回属性
     *
     * @param pKey 键名
     * @return Timestamp 键值(yyyy-MM-dd HH:mm:ss)
     */

    public Timestamp getTimestamp(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "Timestamp", "yyyy-MM-dd HH:mm:ss");
        if (obj != null)
            return (Timestamp) obj;
        else
            return null;
    }

    /**
     * 以Boolean类型返回属性
     *
     * @param pKey 键名
     * @return Boolean 键值
     */

    public Boolean getBoolean(String pKey) {
        Object obj = TypeConvertUtil.convert(get(pKey), "Boolean", null);
        if (obj != null)
            return (Boolean) obj;
        else
            return null;
    }

    /**
     * 以List类型返回属性
     *
     * @param pKey 键名
     * @return List 键值
     */
    public List<? extends Object> getList(String pKey) {
        return (List<? extends Object>) get(pKey);
    }


    /**
     * 控制台打印DTO对象,辅助调试
     */

    public void println() {
        System.out.println(UConstant.CONSOLE_FLAG1 + this.toString());
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }

}
