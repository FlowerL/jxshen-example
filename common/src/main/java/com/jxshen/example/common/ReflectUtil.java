package com.jxshen.example.common;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

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
        //泛型类型 T K V等
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
    
    //获取泛型类中的实际泛型参数
    public static Type getActualType(Type genericType) {
        if (genericType == null) {
            return null;
        }
        if (genericType instanceof ParameterizedType) { //如果是泛型类
            ParameterizedType pType = (ParameterizedType) genericType;
            Type actualType = pType.getActualTypeArguments()[0];
            
            if (actualType instanceof TypeVariable) { //泛型对象 T
                TypeVariable<?> typeVariable = (TypeVariable<?>) actualType;
                return typeVariable;
            }
            
            if (actualType instanceof WildcardType) { //通配符?类型
                WildcardType wildcardType = (WildcardType) actualType;
                return wildcardType;
            }
            
            if (actualType instanceof Class) {  //普通类型对象
                Class<?> clazz = (Class<?>) actualType;
                return clazz;
            }
        }
        return genericType;
    }
}
