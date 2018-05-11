package com.jxshen.example.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jxshen on 2018/05/11
 */
public class MultiIncDec {

    private static AtomicInteger i = new AtomicInteger(0);

    private static class IncJob implements Runnable{

        @Override
        public void run() {
            while (true) {
                int tmp = i.get();
                if (tmp == 0) {
                    i.compareAndSet(tmp, 1);
                    System.out.println(i.get());
                }
                else if (tmp == -1) {
                    i.compareAndSet(tmp, 0);
                    System.out.println(i.get());
                }
            }
        }
    }

    private static class DecJob implements Runnable{

        @Override
        public void run() {
            while (true) {
                int tmp = i.get();
                if (tmp == 0) {
                    i.compareAndSet(tmp, -1);
                    System.out.println(i.get());
                }
                else if (tmp == 1) {
                    i.compareAndSet(tmp, 0);
                    System.out.println(i.get());
                }
            }
        }
    }

    public static void main(String[] args) {
        IncJob incJob = new IncJob();
        DecJob decJob = new DecJob();
        Thread incT = new Thread(incJob);
        Thread decT = new Thread(decJob);
        incT.start();
        decT.start();
    }
}
