package com.jxshen.example.reflect;

import java.lang.reflect.Field;

public class MultiInnerClassReflect {

    public class FirstInner {
        public class SecondInner {
            public class ThirdInner {
                
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        MultiInnerClassReflect outer = new MultiInnerClassReflect();
        FirstInner first = outer.new FirstInner();
        FirstInner.SecondInner second = first.new SecondInner();
        FirstInner.SecondInner.ThirdInner third = second.new ThirdInner();
        
        Field outerField = FirstInner.class.getDeclaredField("this$0");
        Object object = outerField.get(first);
        System.out.println(object == outer);
        
        Field firstInnerField = second.getClass().getDeclaredField("this$1");
        object = firstInnerField.get(second);
        System.out.println(object ==  first);
        
        Field secondInnerField = third.getClass().getDeclaredField("this$2");
        object = secondInnerField.get(third);
        System.out.println(object == second);
        
    }
}
