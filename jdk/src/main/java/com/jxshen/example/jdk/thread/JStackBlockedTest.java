package com.jxshen.example.jdk.thread;

public class JStackBlockedTest {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        new Thread(task, "A").start();
        new Thread(task,"B").start();
    }

    private static class MyTask implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
