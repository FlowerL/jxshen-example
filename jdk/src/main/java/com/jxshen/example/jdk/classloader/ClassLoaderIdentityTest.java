package com.jxshen.example.jdk.classloader;

import java.lang.reflect.Method;

public class ClassLoaderIdentityTest {

    private static final String path = "D:\\git\\jxshen-example\\jdk\\target\\classes";
    private static final FileSystemClassLoader fscl1 = new FileSystemClassLoader(path);
    private static final FileSystemClassLoader fscl2 = new FileSystemClassLoader(path);

    public static void main(String[] args) {
        String className = "com.jxshen.example.jdk.classloader.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Class<?> class2 = fscl2.loadClass(className);
            Object obj1 = class1.newInstance();
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            System.out.println(class1.equals(class2));
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
