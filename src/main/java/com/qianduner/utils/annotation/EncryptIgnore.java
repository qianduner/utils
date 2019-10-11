package com.qianduner.utils.annotation;

import java.lang.annotation.*;

/**
 * 接口数据加密
 *
 * @author Laver
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptIgnore {
}
