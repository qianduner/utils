package com.qianduner.utils.annotation;

import java.lang.annotation.*;

/**
 * api接口，忽略Token验证
 * @author Laver
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
