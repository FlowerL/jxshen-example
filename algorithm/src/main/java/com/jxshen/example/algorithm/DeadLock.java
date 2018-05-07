package com.jxshen.example.algorithm;

import java.util.concurrent.CountDownLatch;

/**
 * @author jxshen on 2018/05/04
 */
public class DeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " enter lock1");
                    latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + " enter lock2");
                    }
                }
            }
        };
        thread1.start();
        Thread.sleep(5000);
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " enter lock2");
                    latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + " enter lock1");
                    }
                }
            }
        };
        thread2.start();
    }
}
