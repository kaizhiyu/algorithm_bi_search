package com.algorithm.$8_annotation;

import com.algorithm.$8_annotation.repeatable.SearchKeyWords;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auth v_fanhaibo on   2018/1/11.
 */

@Retention(RetentionPolicy.SOURCE)
@Repeatable(SearchKeyWords.class)
public @interface SearchKeyWord {

    String value() default "";
}
