package com.jxshen.example.proxy.cglib;

public class CglibProxyTest {

    public static void main(String[] args) {
        CglibProxyClass proxy = new CglibProxyClass();
        BaseClass base = CglibProxyFactory.getInstance(proxy);
        base.add();
    }
}
