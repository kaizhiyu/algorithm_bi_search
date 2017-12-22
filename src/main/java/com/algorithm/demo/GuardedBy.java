package com.algorithm.demo;

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
@Retention(RetentionPolicy.CLASS)
public @interface GuardedBy {
    String value();
}
