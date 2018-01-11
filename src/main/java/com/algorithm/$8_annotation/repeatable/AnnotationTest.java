package com.algorithm.$8_annotation.repeatable;

import com.algorithm.$8_annotation.single.ann.GuardedBy;

import java.util.Arrays;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2017/12/22.
 * @version:v1.0
 */

@GuardedBy("test")
@GuardedBy("Annotation")
@GuardedBy("van")
public class AnnotationTest {
    /**
     *  测试获取标签内容
     * @param args
     */
    //must be @Retention(RetentionPolicy.RUNTIME)
    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        GuardedBy[] annotationsByType = annotationTest.getClass().getAnnotationsByType(GuardedBy.class);
        Arrays.stream(annotationsByType).map(GuardedBy::value).forEach(System.out::println);


    }
}
