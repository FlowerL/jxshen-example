package com.jxshen.example.reflect.field;

import java.util.List;

/**
 * 用来测试Field的class类
 * @author jxshen
 *
 */
public class FieldObject {

    //普通成员变量
    private int normalField;
    
    //静态成员变量
    public static String staticField;
    
    //final成员变量
    public final Object finalField = new Object();
    
    //注解成员变量
    @FieldAnnotation1
    @FieldAnnotation2
    private String annotationField;
    
    //泛型成员变量
    public List<String> genericField;
}
