package com.jxshen.example.jdk.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockThreadStateTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(new LockTask(), "LockTask").start();
        new Thread(new BlockTask(), "BlockTask").start();
    }

    private static class LockTask implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.print("LockTask obtain lock");
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.print("LockTask release lock");
                lock.unlock();
            }
        }
    }

    private static class BlockTask implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.print("BlockTask obtain lock");
            } finally {
                System.out.print("BlockTask release lock");
                lock.unlock();
            }
        }
    }


}

