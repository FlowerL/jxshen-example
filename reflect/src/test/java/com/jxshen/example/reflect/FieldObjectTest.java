package com.jxshen.example.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.junit.Test;

import com.jxshen.example.common.ReflectUtil;

public class FieldObjectTest {

    @Test
    public void doTest() throws Exception {
        FieldObject fo = new FieldObject();
        Class<? extends FieldObject> clazz = fo.getClass();
        
        //普通成员变量操作
        Field normalField = clazz.getDeclaredField("normalField"); //declared能取到私有成员
        normalField.setAccessible(true);//改变成员可见范围，即便没有getter，setter，也可以对其设值取值
        normalField.set(fo, 1);
        System.out.println("normal field : " + normalField.getInt(fo));
        
        //静态成员变量操作
        Field staticField = clazz.getField("staticField"); //public成员用getXXX和getDeclaredXXX都可以
//        staticField.setAccessible(true); 
        staticField.set(null, "static test"); //静态成员obj参数传null
        System.out.println("static field : " + staticField.get(null));
        
        //final成员变量
        Field finalField = clazz.getField("finalField");
        finalField.setAccessible(true);  //setAccessible对final成员可以突破final限制重新赋值
        finalField.set(fo, "jxshen");
        System.out.println("final field : " + finalField.get(fo));
        
        //注解成员变量
        Field annotationField = clazz.getDeclaredField("annotationField");
        annotationField.setAccessible(true);
        //注解类的@Retention的RetentionPolicy必须为RUNTIME才能通过反射获得
        Annotation[] annotations = annotationField.getAnnotations(); //取属性上的所有注解(包含继承，并且父类注解必须是可以被继承的)
        Annotation[] declaredAnnotations = annotationField.getDeclaredAnnotations(); //取属性上直接声明的注解(不包含继承)
        System.out.println("annotation field : " + Arrays.toString(annotations));
        System.out.println("declared annotation field : " + Arrays.toString(declaredAnnotations));
        
        //泛型类成员变量
        Field genericField = clazz.getField("genericField"); //注意此时还只是一般的class而不是type
        System.out.println("generic field : " + genericField);
        System.out.println("generic field type: " + genericField.getType()); //不带泛型的type
        Type genericType = genericField.getGenericType();
        System.out.println("generic field generic type: " + genericType); //如果是泛型，此时类上带泛型参数
        System.out.println("generic field generic actual type: " + ReflectUtil.getActualType(genericType));
    }
}
