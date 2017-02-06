package com.jxshen.example.reflect.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.jxshen.example.common.ReflectUtil;
import com.jxshen.example.reflect.method.MethodObject;

public class MethodObjectTest {

    @Test
    public void doTest() throws Exception {
        MethodObject mo = new MethodObject();
        Class<? extends MethodObject> clzz = mo.getClass();
        System.out.println("class method is : " + Arrays.toString(clzz.getDeclaredMethods()));
        Method method = clzz.getDeclaredMethod("add", int.class, List.class, Object[].class);
        System.out.println("add method is : " + method);
        
        //开始获取一个完整方法上的各种信息
        //函数的可见性好像并不影响其任何属性的反射获取
        
        //获取方法上的注解, 即便是private函数，getAnntotaions和getDeclaredAnnotations都可以获取方法注解
        Annotation[] annotations = method.getAnnotations();
        System.out.println("method annotation is : " + Arrays.toString(annotations));
        
        //获取方法修饰符
        int modifiers = method.getModifiers();
        String modify = Modifier.toString(modifiers);
        System.out.println("method modifier is " + modify);
        
        //方法上定义的泛型参数
        TypeVariable<Method>[] typeParameters = method.getTypeParameters(); //获得函数泛型的参数
        System.out.println("method typeParameters is " + Arrays.toString(typeParameters));
        Class<?>[] parameterTypes = method.getParameterTypes(); //获得函数参数的type
        System.out.println("method parameterTypes is " + Arrays.toString(parameterTypes));
        
        //方法的返回值
        Class<?> returnType = method.getReturnType();
        System.out.println("method return type is " + returnType);
        Type genericReturnType = method.getGenericReturnType();
        System.out.println("method generic return type is " + genericReturnType);
        
        //方法名称
        String methodName = method.getName();
        System.out.println("method name is " + methodName);
        
        //获得方法的泛型参数
        parameterTypes = method.getParameterTypes(); //获得方法所有参数,class形式
        System.out.println("method parameterTypes is " + Arrays.toString(parameterTypes));
        Type[] genericParameterTypes = method.getGenericParameterTypes(); //获得方法所有参数，泛型形式genericType
        System.out.println("method generic parameterTypes is " + Arrays.toString(genericParameterTypes));
        for (Type type : genericParameterTypes) {
            System.out.println("method generic paremeter's actual type is  " + ReflectUtil.getActualType(type));
        }
        
        //方法的参数注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        System.out.println("method parameter annotations are " + Arrays.deepToString(parameterAnnotations));
        
        //方法抛出的异常
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        System.out.println("method exception types are " + Arrays.toString(exceptionTypes));
        Type[] genericExceptionTypes = method.getGenericExceptionTypes();
        System.out.println("method generic exception types are " + Arrays.toString(genericExceptionTypes));
        
        //开始对方法通过反射调用
        method.setAccessible(true); //反射调用方法会受到方法可见性影响，需要设置accessible
        List<String> list = new ArrayList<String>();
        method.invoke(mo, 1,list, new String[] {"1", "2"});
        System.out.println(list);
        
        //方法定义所在的类
        Class<?> declaringClass = method.getDeclaringClass();
        System.out.println("The method is defined in : " + declaringClass);
        
        //方法的一些属性判断
        System.out.println(String.format("The method is Bridge = %b"
                + " is Default = %b"
                + " is Synthetic = %b"
                + " is VarArgs = %b", 
                method.isBridge(),
                method.isDefault(),
                method.isSynthetic(),
                method.isVarArgs()));
    }
    
}
