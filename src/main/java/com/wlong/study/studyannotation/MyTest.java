package com.wlong.study.studyannotation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@MyAnnotationA(a2 = "测试")
public class MyTest {

    private String name="";
    private String age;


    @org.junit.Test
    public void test(){
        MyTest test =new MyTest();
        Class testClass=test.getClass();
        Annotation[] annotations=testClass.getAnnotations();
        Field[] fields=testClass.getDeclaredFields();
        //这种方式获取 @AliasFor 别名注解可以起作用
        MyAnnotationA testMyAn=AnnotationUtils.findAnnotation(getClass(),MyAnnotationA.class);
        System.out.println("通过AnnotationUtils获取的a1---->"+testMyAn.a1());
        System.out.println("通过AnnotationUtils获取的a2---->"+testMyAn.a2());
        for(Annotation a:annotations){
            if(a instanceof  MyAnnotationA){
                //反射方式获取 @AliasFor 别名注解不起作用
                MyAnnotationA annotation = (MyAnnotationA) testClass.getAnnotation(MyAnnotationA.class);
                System.out.println("反射方式获取的a1---->"+annotation.a1());
                System.out.println("反射方式获取的a2---->"+annotation.a2());
            }
        }
        for(Field filed:fields){
            System.out.println(filed.getName());
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
