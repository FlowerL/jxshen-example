package com.jxshen.example.jdk.thread;

public class JoinWaitingTest {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepTask(), "SleepThread");
        sleepThread.start();
        sleepThread.join();
    }

    private static class SleepTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
