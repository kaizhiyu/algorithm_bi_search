package com.algorithm.$8_annotation;

import com.algorithm.$8_annotation.repeatable.GuardedBys;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */
@Repeatable(GuardedBys.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy {
    String value();
}
