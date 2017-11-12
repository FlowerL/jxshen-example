package com.jxshen.example.jdk.thread;

public class JStackDeadLockTest {

    private static final Object monitorA = new Object();
    private static final Object monitorB = new Object();

    private static class MyTask1 implements Runnable {

        @Override
        public void run() {
            synchronized (monitorA) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (monitorB) {

                }
            }
        }
    }

    private static class MyTask2 implements Runnable {

        @Override
        public void run() {
            synchronized (monitorB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (monitorA) {

                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyTask1(), "A").start();
        new Thread(new MyTask2(), "B").start();
    }
}
