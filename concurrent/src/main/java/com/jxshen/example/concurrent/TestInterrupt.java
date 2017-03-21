package com.jxshen.example.concurrent;

/**
 * code reference from: http://blog.csdn.net/guorain32188/article/details/11944323<br>
 * The result on the above url "A1 B2 B3 B4 B5" is wrong.<br>
 * The accurate result is "B2 ..B5 A1" middle order is randomly by B3, B5+java.lang.IllegalThreadStateException<br>
 * Because:<br>
 * Thread1: interrupt function do not interrupt a thread running. so A1 always occurs after 2s<br>
 * Thread2: sleep function do not need a lock to enter or lose any owned lock, but could be<br>
 *          interruptted immediately when interrupt function is called.<br>
 * Thread3: wait function should get lock first otherwise it throws java.lang.IllegalMonitorStateException<br>
 *          so in this example it will throw exception immediately when call wait —— wait should be used in<br>
 *          synchronized block.<br>
 * Thread4: wait is in synchronized code so wait function could be called normally but will be intterrupted<br>
 *          immediately when interrupt function is called. But since it use synchronized so B4 show the last<br>
 *          just before A1
 * Thread5: thread4 repulicatedly call start so it throws java.lang.IllegalThreadStateException
 *                   
 */
public class TestInterrupt {

    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() - time < 2000) {}
                    System.out.println("A1");
                } catch (Exception e) {
                    System.out.println("B1");
                }
            }
        };
        thread1.start();
        thread1.interrupt();
        
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("A2");
                } catch (InterruptedException e) {
                    System.out.println("B2");
                }
            }
        };
        thread2.start();
        thread2.interrupt();
        
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
                    this.wait(2000);
                    System.out.println("A3");
                } catch (Exception e) {
                    System.out.println("B3");
                }
            }
        };
        thread3.start();
        thread3.interrupt();
        
        Thread thread4 = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this) {
                        this.wait(2000);
                        System.out.println("A4");
                    }
                } catch (InterruptedException e) {
                    System.out.println("B4");
                }
                
            }
        };
        thread4.start();
        thread4.interrupt();
        
        try {
            thread4.start();
            System.out.println("A5");
        } catch (Exception e) {
            System.out.println("B5");
            System.out.println(e.toString());
        }
    }
}
