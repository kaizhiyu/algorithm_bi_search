package com.algorithm.$8_annotation;

import java.lang.annotation.*;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */

@Repeatable(ParaDescs.class)
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.LOCAL_VARIABLE,ElementType.METHOD})
public @interface ParaDesc {
    String value() default "";
}
