package com.jxshen.example.concurrent;

import org.junit.Test;

public class ReorderTest {

    @Test
    public void doTest() {
        for (int i = 0; i < 10000; i++) {
            int x, y;
            x = 1;
            try {
                x = 2;
                y = 0 / 0;
            } catch (Exception e) {
                if (x == 1) {
                    System.out.println("x = " + x);
                }
            } finally {
//                System.out.println("x = " + x);
            }
        }
    }
}
