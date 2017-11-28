package com.jxshen.example.reflect.clazz;

public class NullInstanceTest {

    public static void main(String[] args) {
        System.out.println(null instanceof Object);
        Class<Object> objClzz = Object.class;
        System.out.println(objClzz.isInstance(null));
        Object obj = new Object();
        System.out.println(obj.getClass().equals(null));
    }
}
