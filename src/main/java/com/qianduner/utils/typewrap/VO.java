package com.qianduner.utils.typewrap;

import com.alibaba.fastjson.JSON;
import com.qianduner.utils.constant.UConstant;
import com.qianduner.utils.gather.group.U;
import com.qianduner.utils.typewrap.impl.HashDto;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * <b>值对象</b>
 */
public class VO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 将当前对象转换为Dto对象
     *
     * @return dto 返回的Dto对象
     */
    public Dto toDto() {
        Dto dto = new HashDto();
        U.copyProperties(this, dto);
        return dto;
    }


    /**
     * 将当前对象转换为JSON字符串
     *
     * @return String 返回的JSON格式字符串
     */
    public String toJson() {
        String jsonString = JSON.toJSONString(this);
        return jsonString;
    }

    /**
     * 将参数对象中的属性复制到源对象中
     * @param inObj 复制参数
     */
    public void copyProperties(Object inObj) {
        U.copyProperties(inObj, this);
    }

    /**
     * 清除当前对象属性
     */
    public void clear() {
        Method[] methods = this.getClass().getMethods();
        for (int i = 0, n = methods.length; i < n; i++) {
            try {
                Method method = methods[i];
                if ((method.getModifiers() & Modifier.PUBLIC) == 1) {
                    String methodName = method.getName();
                    if (methodName.startsWith("set")) {
                        method.invoke(this, new Object[]{null});
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将此对象以可读形式打印输出
     */
    public void println() {
        System.out.println(UConstant.CONSOLE_FLAG1 + toJson());
    }
}
