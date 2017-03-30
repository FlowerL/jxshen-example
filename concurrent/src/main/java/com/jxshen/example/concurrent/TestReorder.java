package com.jxshen.example.concurrent;

/**
 * Memory reorder example.<br>
 * reference from : http://tech.meituan.com/java-memory-reordering.html
 *
 */
public class TestReorder {
    
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            
            x = 0; y = 0;
            a = 0; b = 0;
            
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            
            one.start();
            other.start();
            one.join();
            other.join();
            
            if ( (x == 0 && y == 0) || (x == 1 && y == 1))
            {
                System.out.println(String.format("x = %d, y = %d", x, y));
            }
        }
    }
}
