package com.jxshen.example.concurrent;

/**
 * reference from : http://www.cnblogs.com/aboutblank/p/3631453.html
 */
public class TestJoin implements Runnable {
    
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new TestJoin());
        long start = System.currentTimeMillis();
        t.start();
        t.join(1000);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Main finished");
    }

    @Override
    public void run() {
//        synchronized (Thread.currentThread()) {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡眠" + i);
            }
            System.out.println("TestJoin finished");
//        }
    }

    
}
