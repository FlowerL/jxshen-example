package com.jxshen.example.jdk.lock;

public class MultiSynchronizedTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        Runnable task1 = new Task1();
        Thread thread1 = new Thread(task1, "task1");
        thread1.start();
        Runnable task2 = new Task2();
        Thread thread2 = new Thread(task2, "task2");
        thread2.start();
    }

    private static class Task1 implements Runnable {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Task1 obtain lock1");
                synchronized (lock2) {
                    System.out.println("Task1 obtain lock2");
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class Task2 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("Task2 obtain lock2");
            }
        }
    }
}
