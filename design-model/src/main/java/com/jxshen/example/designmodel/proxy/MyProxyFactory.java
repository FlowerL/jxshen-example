package com.jxshen.example.designmodel.proxy;

import java.lang.reflect.Proxy;

/**
 * Proxy Model<br>
 * create proxy object<br>
 * Refernece from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class MyProxyFactory {

    public static Object getProxy(Object target) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setTarget(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
                target.getClass().getInterfaces(), handler);
    }
}
