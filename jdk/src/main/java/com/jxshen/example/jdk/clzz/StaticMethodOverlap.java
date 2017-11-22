package com.jxshen.example.jdk.clzz;

public class StaticMethodOverlap {

    public static void main(String[] args) {
        Interf interf = new Parent();
        interf.show();
        Interf.staticShow();
        Interf interf1 = new Son();
        interf1.show();
        Parent p = new Parent();
        p.show();
        Parent.staticShow();
        Parent p1 = new Son();
        p1.show();
        Son son = new Son();
        son.show();
        Son.staticShow();
    }

    private interface Interf {
        default void show() {
            System.out.println("Interf default show");
        }
        static void staticShow() {
            System.out.println("Interf static show");
        }
    }

    private static class Parent implements Interf {
        public void show() {
            System.out.println("Parent default show");
        }
        public static void staticShow() {
            System.out.println("Parent static show");
        }
    }

    private static class Son extends Parent {
        public void show() {
            System.out.println("Son default show");
        }
        public static void staticShow() {
            System.out.println("Son static show");
        }
    }
}
