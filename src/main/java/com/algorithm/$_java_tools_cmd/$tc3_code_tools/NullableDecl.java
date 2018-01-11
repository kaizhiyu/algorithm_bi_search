package com.algorithm.$_java_tools_cmd.$tc3_code_tools;

import com.algorithm.$8_annotation.single.ann.Doc4Desc;

import java.lang.annotation.*;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/25.
 * @version:v1.0
 */
@Documented
@Doc4Desc("说明该字段可能为空")
@Retention(RetentionPolicy.CLASS)
public @interface NullableDecl {
    String value() default "";
}
