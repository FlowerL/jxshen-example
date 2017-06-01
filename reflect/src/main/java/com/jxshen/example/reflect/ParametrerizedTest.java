package com.jxshen.example.reflect;

import java.lang.reflect.ParameterizedType;

public class ParametrerizedTest {
    
    class A1 {}
    
    class A2<T> extends A1 {
        public void foo() {}
        
        public void fun2() {
            System.out.println(((ParameterizedType)this.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0]);
        }
    }

    class A3<T> extends A2<Float> {
        public void foo() {
            System.out.println(((ParameterizedType)this.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0]);
        }
    }
    
    public static void main(String[] args) {
        ParametrerizedTest test = new ParametrerizedTest();
        test.new A3<Integer>().fun2();
    }
}
