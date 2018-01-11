package com.algorithm.$8_annotation.single.ann;

import com.algorithm.$8_annotation.repeatable.TODOLists;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auth v_fanhaibo on   2018/1/11.
 */



@Retention(RetentionPolicy.SOURCE)
@Repeatable(TODOLists.class)
public @interface TODOList {
    String value();
}
