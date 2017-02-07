package com.jxshen.example.reflect.compile;

public class SyntheticObject {

    //内部私有静态类
    private static class Inner{}
    
    static void checkSynthetic(String className) {
        try {
            System.out.println(className + " : " + Class.forName(className).isSynthetic());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void doRun(){
        Inner inner = new Inner();
        checkSynthetic("com.jxshen.example.reflect.compile.SyntheticObject");
        for(Class<?> clazz : inner.getClass().getClasses()) {
            checkSynthetic(clazz.getName());
        }
    }
}
