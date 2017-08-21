package com.jxshen.example.reflect;

import java.lang.reflect.Field;

public class InnerClassReflect {

    public String getOuterClassName() throws Exception {
        class LocalClass {};
        
        Field outerClassField = LocalClass.class.getDeclaredField("this$0");
        return outerClassField.getType().getTypeName();
    }
    
    public InnerClassReflect getOuterClassInstance() throws Exception {
        class LocalClass {};
        
        LocalClass local = new LocalClass();
        Field outerClassField = LocalClass.class.getDeclaredField("this$0");
        return (InnerClassReflect)outerClassField.get(local);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(new InnerClassReflect().getOuterClassName());
        InnerClassReflect inner = new InnerClassReflect();
        System.out.println(inner.getOuterClassInstance() == inner);
    }
}
