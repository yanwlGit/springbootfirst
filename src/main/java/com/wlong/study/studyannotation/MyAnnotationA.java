package com.wlong.study.studyannotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyAnnotationA {

    @AliasFor("a2")
    String a1() default "";

    @AliasFor("a1")
    String a2() default "";

}