package com.jxshen.example.jdk.clzz;

public class ExtendA extends AbstractA {

    public static void main(String[] args) {
        InterfA interfA = new ExtendA();
        interfA.execute();
        ExtendA extendA = new ExtendA();
        System.out.println(interfA.getClass() == InterfA.class);
        System.out.println(extendA instanceof InterfA);
    }
}
