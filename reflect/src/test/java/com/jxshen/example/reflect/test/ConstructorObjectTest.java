package com.jxshen.example.reflect.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.jxshen.example.reflect.constructor.ConstructorObject;

public class ConstructorObjectTest {

    @Test
    public void doTest() throws Exception {
        //获取该类公有构造函数
        Constructor<?>[] constructors = ConstructorObject.class.getConstructors();
        System.out.println("get constructors are : " + Arrays.toString(constructors));
        
        //获取该类声明的所有构造函数
        constructors = ConstructorObject.class.getDeclaredConstructors();
        System.out.println("get declared constructors are : " + Arrays.toString(constructors));
        
        //获得指定的构造函数
        Constructor<ConstructorObject> constructor = ConstructorObject.class.getDeclaredConstructor(List.class, Object[].class);
        System.out.println("The constructor's accessible is " + constructor.isAccessible());
        
        //获取构造函数信息与构造函数是否可见(public, private)无关，只有调用函数时需要可见
        //判断是否有该注解
        System.out.println("SafeVarargs is present on constructor is " + constructor.isAnnotationPresent(SafeVarargs.class));
        
        //获取该构造函数上申明的所有注解
        Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
        System.out.println("constructor declare annotations are : " + Arrays.toString(declaredAnnotations));
        
        //获取构造函数上的修饰符
        int modifiers = constructor.getModifiers();
        System.out.println("The modifiers of constructor are : " + Modifier.toString(modifiers));
        
        //获取构造函数定义的泛型变量 (不是构造函数使用的泛型变量)
        TypeVariable<Constructor<ConstructorObject>>[] typeVariables = constructor.getTypeParameters();
        System.out.println("The type parameters of constructor are : " + Arrays.toString(typeVariables));
        
        //构造方法名
        System.out.println("The name of constructor is : " + constructor.getName());
        
        //获得构造函数参数的Class类型
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        System.out.println("The parameter types of constructor are : " + Arrays.toString(parameterTypes));
        
        //获得构造函数参数的Type类型
        Type[] genericParameterTypes = constructor.getGenericParameterTypes();
        System.out.println("The generic parameter types of constructor are : " + Arrays.toString(genericParameterTypes));
        
        for (Type type : genericParameterTypes) {
            
        }
    }
}
