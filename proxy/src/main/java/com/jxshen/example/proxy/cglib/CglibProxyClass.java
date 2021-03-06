package com.jxshen.example.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyClass implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before--------------------");
//        method.invoke(o, objects);
        methodProxy.invokeSuper(o, objects);
        System.out.println("after----------------------");

        return null;
    }
}
