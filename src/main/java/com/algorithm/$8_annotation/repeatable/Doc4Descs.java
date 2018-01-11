package com.algorithm.$8_annotation.repeatable;

import com.algorithm.$8_annotation.Doc4Desc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Doc4Descs {
    Doc4Desc[] value();
}
