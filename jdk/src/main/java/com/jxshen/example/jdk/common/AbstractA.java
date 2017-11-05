package com.jxshen.example.jdk.common;

public abstract class AbstractA implements InterfA {

    public AbstractA() {
    }

    public AbstractA(int i){}

    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName());
    }
}
