package com.jxshen.example.reflect.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import com.jxshen.example.reflect.clazz.OuterClass;
import com.jxshen.example.reflect.clazz.OuterClass.InnerClass;
import com.jxshen.example.reflect.clazz.OuterClass.InnerInterface;

public class OuterClassTest {

    @Test
    public void testGetEnclosing() {
        System.out.println("内部类");
        OuterClass.getEnclosing(InnerClass.class);
        
        System.out.println("匿名内部类");
        OuterClass.innerClass.fun();
        
        System.out.println("方法中的匿名内部类");
        OuterClass.doFun();
        
        System.out.println("构造函数里的匿名内部类");
        new OuterClass();
    }
    
    @Test
    public void testGetDeclaring() {
        Class<?> declaringClass = InnerClass.class.getDeclaringClass();
        System.out.println("InnerClass is defined in : " + declaringClass);
        
        declaringClass = InnerInterface.class.getDeclaringClass();
        System.out.println("InnerInterface is defined in : " + declaringClass);
    }
    
    /**
     * Class.getDeclaredXXX 能获取私有成员，但不能获取继承成员和匿名成员
     * Class.getXXX 能获取继承成员，但不能获取私有成员和匿名成员
     */
    @Test
    public void testGetDecared() {
        Class<?>[] declaredClasses = OuterClass.class.getDeclaredClasses();
        System.out.println("getDeclaredClasses方法获取类中定义的类成员:");
        System.out.println(Arrays.toString(declaredClasses));
        
        Class<?>[] classes = OuterClass.class.getClasses();
        System.out.println("getClasses方法获取类中定义的类成员:");
        System.out.println(Arrays.toString(classes));
        
        Annotation[] declaredAnnotations = OuterClass.class.getDeclaredAnnotations();
        System.out.println("getDeclaredAnnotations方法获取类上直接使用的:");
        System.out.println(Arrays.toString(declaredAnnotations));
        
        Constructor<?>[] declaredConstructors = OuterClass.class.getDeclaredConstructors();
        System.out.println("getDeclaredConstructors方法获取类的所有构造函数:");
        System.out.println(Arrays.toString(declaredConstructors));
        
        Field[] declaredFields = OuterClass.class.getDeclaredFields();
        System.out.println("getDeclaredConstructors方法获取类的所有成员变量:");
        System.out.println(Arrays.toString(declaredFields));
        
        Method[] declaredMethods = OuterClass.class.getDeclaredMethods();
        System.out.println("getDeclaredConstructors方法获取类的所有方法:");
        System.out.println(Arrays.toString(declaredMethods));
    }
}
