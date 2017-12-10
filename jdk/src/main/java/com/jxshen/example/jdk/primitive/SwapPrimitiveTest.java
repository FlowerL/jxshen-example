package com.jxshen.example.jdk.primitive;

import java.lang.reflect.Field;

public class SwapPrimitiveTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
////        int a = 1;
////        int b = 2;
////        swapInt(1, 2);
////        printSwap(a, b);
//
//        Integer aa = 1;
//        Integer bb = 2;
////        swapInteger(aa, bb);
////        printSwap(aa, bb);
//
//        swapIntegerReflect(aa, bb);
//        printSwap(aa, bb);
//
//        Integer c = 1;
//        Integer d = 2;
//        printSwap(c, d);

        int one = 1;
        int two = one + one;
        System.out.printf("Two = %d\r\n", two);
    }

    private static void swapInt(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    private static void swapInteger(Integer a, Integer b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    private static void swapIntegerReflect(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        int temp = value.getInt(a);
        value.setInt(a, b.intValue());
        value.setInt(b, temp);
    }

    private static void printSwap(int a, int b) {
        System.out.printf("a = %d, b = %d\r\n", a, b);
    }
}
