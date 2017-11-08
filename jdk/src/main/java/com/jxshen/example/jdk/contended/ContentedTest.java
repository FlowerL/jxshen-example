package com.jxshen.example.jdk.contended;

import sun.misc.Contended;

public class ContentedTest {

    private static class VolatileLong {
        @Contended("group1")
        public volatile long group1_1 = 0L;
        @Contended("group1")
        public volatile long group1_2 = 0L;
        @Contended("group2")
        public volatile long group2_1 = 0L;
        @Contended("group2")
        public volatile long group2_2 = 0L;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("starting....");

        VolatileLong volatileLong = new VolatileLong();
        long start = System.nanoTime();
        Thread t0 = new Thread(new NonFalseSharingTask("t0"));
        Thread t1 = new Thread(new NonFalseSharingTask("t1"));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.println("duration = " + (System.nanoTime() - start));

        System.out.println("starting....");
        start = System.nanoTime();
        t0 = new Thread(new FalseSharingTask("t0"));
        t1 = new Thread(new FalseSharingTask("t1"));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static class NonFalseSharingTask implements Runnable {

        public final static long ITERATIONS = 500L * 1000L * 1000L;
        static VolatileLong volatileLong = new VolatileLong();
        String groupId;

        public NonFalseSharingTask(String groupId) {
            this.groupId = groupId;
        }

        @Override
        public void run() {
            long i = ITERATIONS + 1;
            if (groupId.equals("t0")) {
                while (0 != --i) {
                    volatileLong.group1_1 = i;
                    volatileLong.group1_2 = i;
                }
            } else if (groupId.equals("t1")) {
                while (0 != --i) {
                    volatileLong.group2_1 = i;
                    volatileLong.group2_2 = i;
                }
            }
        }
    }

    private static class FalseSharingTask implements Runnable {
        public final static long ITERATIONS = 500L * 1000L * 1000L;
        static VolatileLong volatileLong = new VolatileLong();
        String groupId;

        public FalseSharingTask(String groupId) {
            this.groupId = groupId;
        }

        @Override
        public void run() {
            long i = ITERATIONS + 1;
            if (groupId.equals("t0")) {
                while (0 != --i) {
                    volatileLong.group1_1 = i;
                    volatileLong.group2_1 = i;
                }
            } else if (groupId.equals("t1")) {
                while (0 != --i) {
                    volatileLong.group1_2 = i;
                    volatileLong.group2_2 = i;
                }
            }
        }
    }
}
