package com.jxshen.example.reflect.constructor;

import java.util.List;

/**
 * 构造方法可以被获取各种构造方法信息，并创建对象实例<br>
 * 
 *
 */
public class ConstructorObject<T,E> {

    private List<T> list;
    private E[] params;
    
    //默认构造函数
    public ConstructorObject(){}
    
    //私有构造函数
    private ConstructorObject(List<T> list) {
        this.list = list;
    }
    
    @SafeVarargs
    @ConstructorAnnotation1
    @ConstructorAnnotation2
    private <C> ConstructorObject(
            @ConstructorAnnotation1 @ConstructorAnnotation2 List<T> list,
            E...params) throws RuntimeException, Exception{
        this.list = list;
        this.params = params;
    }
}
