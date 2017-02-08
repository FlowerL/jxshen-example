package com.jxshen.example.reflect.test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jxshen.example.reflect.type.TypeObject;

public class TypeObjectTest {

    @Test
    public void doTest() throws Exception {
        Method method = TypeObject.class.getMethod(
                "fun",
                Object.class,  // K
                Object.class,  // T
                List.class,    //List<? extends V>
                String.class,  //String
                Map.class      //Map<? extends String, Object>
                );
        System.out.println("Return type :" + method.getReturnType());
        System.out.println("ParameterCount :" + method.getParameterCount());
        System.out.println("DefaultValue :" + method.getDefaultValue());
        System.out.println("Type Parameters are :" + Arrays.toString(method.getTypeParameters()));
        System.out.println("ParameterTypes are :" + Arrays.toString(method.getParameterTypes()));
    }
}
