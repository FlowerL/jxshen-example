package com.jxshen.example.jdk.thread;

public class SynchronizeAccessTest {
    private String value;

    private synchronized String getValue() throws InterruptedException {
        Thread.sleep(3000);
        return value;
    }

    public static void main(String[] args) {
        SynchronizeAccessTest obj = new SynchronizeAccessTest();
        new Thread(() -> {
            try {
                System.out.println(obj.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            obj.value = "setup";
            System.out.println(obj.value);
        }).start();
    }
}
