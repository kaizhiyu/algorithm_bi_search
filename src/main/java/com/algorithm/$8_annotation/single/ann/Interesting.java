package com.algorithm.$8_annotation.single.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auth v_fanhaibo on   2018/1/11.
 */

@Doc4Desc("something that is interesting")

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.TYPE})
//@Repeatable(TODOLists.class)

public @interface Interesting {
    String value();
}
