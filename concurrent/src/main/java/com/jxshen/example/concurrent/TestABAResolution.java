package com.jxshen.example.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestABAResolution {

    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedRef = 
            new AtomicStampedReference<Integer>(100,0); //2nd param is initial stamp
    
    public static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(new Runnable() {
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });
        
        Thread intT2 = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println(c3);
            }
        });
        
        intT1.start();intT2.start();
        intT1.join();intT2.join();
        
        Thread refT1 = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedRef.compareAndSet(100, 101, 
                        atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                atomicStampedRef.compareAndSet(101, 100, 
                        atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            }
        });
        Thread refT2 = new Thread(new Runnable() {
            public void run() {
                int stamp = atomicStampedRef.getStamp();
                System.out.println(String.format("before sleep : stamp = %d", stamp));
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("after sleep : stamp = %d", atomicStampedRef.getStamp()));
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, 
                        stamp, stamp + 1);
                System.out.println(c3);
            }
        });
        refT1.start();refT2.start();
    }
}
