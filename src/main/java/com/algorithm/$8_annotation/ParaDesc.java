package com.algorithm.$8_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.LOCAL_VARIABLE)
public @interface ParaDesc {
    String value() default "";
}
