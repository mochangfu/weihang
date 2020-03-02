package com.hg.gama.gamautil;

import java.lang.annotation.*;

/**
 * 设置JsonPath的路径，如/a/b。如果不设置，默认取根路径下等于参数名的字段值
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface JsonParam {
    String value() default "";

    boolean required() default true;
}
