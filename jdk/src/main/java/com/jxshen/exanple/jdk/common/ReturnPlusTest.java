package com.jxshen.exanple.jdk.common;

public class ReturnPlusTest {
    private static int a = 1;

    public static int plusBefore() {
        return ++a;
    }

    public static int plusAfter() {
        return a++;
    }

    public static void main(String[] args) {
        System.out.println(plusBefore());
        System.out.println(plusAfter());
        System.out.println(a);
    }
}
