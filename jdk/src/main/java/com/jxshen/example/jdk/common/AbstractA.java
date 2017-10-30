package com.jxshen.example.jdk.common;

public abstract class AbstractA implements InterfA {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName());
    }
}
