package com.jxshen.example.jdk.clzz;

public class StaticSynchornizedExtendsTest {

    private static class Super {
        public static synchronized void show() throws InterruptedException {
            System.out.println("Super Synchronized Show");
            Thread.sleep(100000L);
        }
    }
}
