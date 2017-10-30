package com.jxshen.example.jdk.thread;

/**
 * reference from: https://jingyan.baidu.com/article/4f34706e3ec075e387b56df2.html
 */
public class FindJavaThreadInTaskManager {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread Name:" + Thread.currentThread().getName());
            }
        });
        thread.start();
    }
}
