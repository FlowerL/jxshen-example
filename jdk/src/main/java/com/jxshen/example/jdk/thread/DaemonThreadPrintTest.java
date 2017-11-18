package com.jxshen.example.jdk.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DaemonThreadPrintTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        UserTask userTask = new UserTask(cyclicBarrier);
        Thread user = new Thread(userTask, "userThread");
        Thread daemon = new Thread(userTask, "daemonThread");
        daemon.setDaemon(true);
        user.start();
        daemon.start();
    }

    private static class UserTask implements Runnable {

        private final CyclicBarrier barrier;

        public UserTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            Thread curThread = Thread.currentThread();

            try {
                if (curThread.isDaemon()) {
                    Thread.sleep(1500L);
                }
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500L);
                    System.out.println(curThread.getName() + ": " + i);
                }
                if (curThread.isDaemon()) {
                    Thread.sleep(11500L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
