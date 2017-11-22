package com.jxshen.example.jdk.thread;

public class BeforeStartedInterruptedTest {

    public static void main(String[] args) {
        Thread thread = new BeforeStartInterruptThread();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        thread.start();
        System.out.println(thread.isInterrupted());
    }

    private static class BeforeStartInterruptThread extends Thread {
        @Override
        public void run() {
            System.out.println(interrupted());
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
