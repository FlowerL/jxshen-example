package com.jxshen.example.common;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ReflectUtil {
    
    public static Class<?> getGenericClass(ParameterizedType parameterizedType, int index){
        Type[] types = parameterizedType.getActualTypeArguments();
        if (types == null || index >= types.length) {
            return null;
        }
        
        Type genericClass = types[index];
        //多层泛型
        if (genericClass instanceof ParameterizedType) { 
            return (Class<?>) ((ParameterizedType) genericClass).getRawType();
        }
        //数组泛型
        if (genericClass instanceof GenericArrayType) {
            return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
        }
        //泛型擦拭对象
        if (genericClass instanceof TypeVariable) {
            return (Class<?>) ((TypeVariable<?>) genericClass).getGenericDeclaration();
        }
        return (Class<?>) genericClass;
    }

//    public static Class getClass(Type type, int index) {
//        if (type instanceof ParameterizedType) {  //泛型类
//            
//        }
//    }
}
