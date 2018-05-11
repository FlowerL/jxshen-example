package com.jxshen.example.concurrent;

/**
 * @author jxshen on 2018/05/11
 */
public class MultiIncDec2 {

    private static Object lck = new Object();
    private static Integer i = 0;

    private static class IncThread extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (lck) {
                    if (i < 1) {
                        i++;
                        System.out.println(i);
                    }
                }
            }
        }
    }

    private static class DecThread extends Thread {

        @Override
        public void run() {
            while (true) {
                synchronized (lck) {
                    if (i > -1) {
                        i--;
                        System.out.println(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        IncThread incThread = new IncThread();
        DecThread decThread = new DecThread();
        incThread.start();
        decThread.start();
    }
}
