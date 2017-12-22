package com.jxshen.example.proxy.cglib;

public class BaseClass {

    public void add() {
        System.out.println(getClass().getSimpleName() + " called add");
    }
}
