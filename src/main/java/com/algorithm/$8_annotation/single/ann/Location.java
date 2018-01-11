package com.algorithm.$8_annotation.single.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auth v_fanhaibo on   2018/1/11.
 */
@Doc4Desc(" 定位文件 ")
@Retention(RetentionPolicy.SOURCE)
//@Repeatable(TODOLists.class)
public @interface Location {
    String value();
}
