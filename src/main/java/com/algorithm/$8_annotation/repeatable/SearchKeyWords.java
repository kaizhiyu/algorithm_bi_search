package com.algorithm.$8_annotation.repeatable;

import com.algorithm.$8_annotation.SearchKeyWord;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auth v_fanhaibo on   2018/1/11.
 */

@Retention(RetentionPolicy.SOURCE)
public @interface SearchKeyWords {
    SearchKeyWord[] value();
}
