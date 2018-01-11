package com.algorithm.$8_annotation.repeatable;

import com.algorithm.$8_annotation.ParaDesc;

import java.lang.annotation.*;

/**
 * 本地变量注释
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.LOCAL_VARIABLE)
public @interface ParaDescs {
    ParaDesc[] value() ;
}
