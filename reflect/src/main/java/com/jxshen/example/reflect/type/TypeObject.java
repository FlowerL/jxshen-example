package com.jxshen.example.reflect.type;

import java.util.List;
import java.util.Map;

/**
 * 测试反射Type类
 * @param <T>
 */
public class TypeObject<T> {

    public static <K, V, T> void fun(
            K k, 
            T t, 
            List<? extends V> list, 
            String str,
            Map<? extends String, Object> map) {}
}
