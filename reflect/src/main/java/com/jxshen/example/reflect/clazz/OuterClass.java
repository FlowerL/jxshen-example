package com.jxshen.example.reflect.clazz;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class OuterClass {
    
    /**
     * 内部静态类
     */
    public static class InnerClass{
        public InnerClass(){}
        public void fun(){}
    }
    
    /**
     * 内部接口
     */
    public interface InnerInterface{}
    
    /**
     * 匿名内部类
     */
    public static InnerClass innerClass = new InnerClass() {
        /** 
         * System.out.println is:<br>
         * enclosingClass : class com.jxshen.example.reflect.OuterClass<br>
         * enclosingConstructor : null<br>
         * enclosingMethod : null<br>
         */
        public void fun() {
            getEnclosing(this.getClass());
        }
    };

    /**
     * 构造函数匿名内部类
     */
    public OuterClass() {
        InnerClass innerClass = new InnerClass() {
            /** 
             * System.out.println is:<br>
             * enclosingClass : class com.jxshen.example.reflect.OuterClass<br>
             * enclosingConstructor : public com.jxshen.example.reflect.OuterClass()<br>
             * enclosingMethod : null<br>
             */
            public void fun() {
                OuterClass.getEnclosing(this.getClass());
            }
        };
        innerClass.fun();
    }
    
    /**
     * 方法中的匿名内部类
     */
    public static void doFun() {
        InnerClass innerClass = new InnerClass() {
            /** 
             * System.out.println is:<br>
             * enclosingClass : class com.jxshen.example.reflect.OuterClass<br>
             * enclosingConstructor : null<br>
             * enclosingMethod : public com.jxshen.example.reflect.OuterClass.doFun()<br>
             */
            public void fun() {
                getEnclosing(this.getClass());
            }
        };
        innerClass.fun();
    }
    
    //结果输出函数
    public static void getEnclosing(Class<?> clazz) {
        Class<?> enclosingClazz = clazz.getEnclosingClass();
        Constructor<?> enclosingConstructor = clazz.getEnclosingConstructor();
        Method enclosingMethod = clazz.getEnclosingMethod();
        System.out.println("enclosingClass : " + enclosingClazz);
        System.out.println("enclosingConstructor : " + enclosingConstructor);
        System.out.println("enclosingMethod : " + enclosingMethod);
    }
}
