package com.jxshen.example.reflect.test;

import java.lang.reflect.Method;

import org.junit.Test;

import com.jxshen.example.reflect.compile.SyntheticObject;

public class SyntheticTest {

    @Test
    public void doTest() throws Exception {
        Main main = new Main();
        main.test();
        Class<?> m = Class.forName("com.jxshen.example.reflect.test.Main");
        Method[] methods = m.getDeclaredMethods();
        for(Method method : methods){
            if(method.isSynthetic()){
                System.out.println("name = " + method.getName());
            }
        }
    }
    
    @Test
    public void doTest2() {
        System.out.println("doTest2:");
        SyntheticObject.doRun();
    }
}

class Main {
    private String h = "hello";
    private class B {
        private B(){
            System.out.println(h);//访问外部类私有字段，会生成一个方法，这句话实现：
            //System.out.println(Main.access$000(var1));
        }
    }
    public void test(){       
        System.out.println("B : " + B.class.isSynthetic());
    }
}