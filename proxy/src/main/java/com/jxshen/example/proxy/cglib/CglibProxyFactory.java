package com.jxshen.example.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxyFactory {

    public static BaseClass getInstance(CglibProxyClass proxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BaseClass.class);
        enhancer.setCallback(proxy);
        BaseClass base = (BaseClass) enhancer.create();
        return base;
    }
}
