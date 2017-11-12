package com.jxshen.example.jdk.thread;

public class JStackObjectWaitTest {

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        new Thread(task, "A").start();
        new Thread(task,"B").start();
    }
}
