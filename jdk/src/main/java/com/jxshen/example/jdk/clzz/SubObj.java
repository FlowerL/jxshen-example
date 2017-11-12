package com.jxshen.example.jdk.clzz;

public class SubObj extends SuperObj {

    public static void staticFunc() {
        System.out.println("static sub");
    }

    public void concreteFunc() {
        System.out.println("============");
        super.concreteFunc();
        System.out.println("concrete sub");
    }

    public static void main(String[] args) {
        SuperObj superObj = new SubObj();
        SubObj subObj = new SubObj();
//        superObj.staticFunc();
//        superObj.concreteFunc();
//        subObj.staticFunc();
//        subObj.concreteFunc();
        ((SuperObj)subObj).concreteFunc();
    }
}
