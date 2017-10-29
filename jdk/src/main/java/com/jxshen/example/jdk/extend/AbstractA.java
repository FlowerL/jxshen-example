package com.jxshen.example.jdk.extend;

public class AbstractA implements InterfA {
    @Override
    public void execute() {
        System.out.println(this.getClass().getSimpleName());
    }
}
