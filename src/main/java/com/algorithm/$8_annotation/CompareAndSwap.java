package com.algorithm.$8_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CAS
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.METHOD})
public @interface CompareAndSwap {
}
