package com.jxshen.example.jdk.lock;

public class BlockedWaitingSwitchTest {

    private static Object lock = new Object();

    public static void main(String[] args) {
        Runnable waitTask = new WaitTask();
        new Thread(waitTask, "waitTask").start();
        Runnable notifyTask = new NotifyTask();
        new Thread(notifyTask, "notifyTask").start();
    }

    private static class WaitTask implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class NotifyTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
                synchronized (lock) {
                    lock.notifyAll();
                    Thread.sleep(Long.MAX_VALUE);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
