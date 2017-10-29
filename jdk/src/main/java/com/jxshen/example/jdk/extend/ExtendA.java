package com.jxshen.example.jdk.extend;

public class ExtendA extends AbstractA {

    public static void main(String[] args) {
        InterfA interfA = new ExtendA();
        interfA.execute();
    }
}
