package com.algorithm.$8_annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Doc4Descs.class)
public @interface Doc4Desc {
    String value();
}
