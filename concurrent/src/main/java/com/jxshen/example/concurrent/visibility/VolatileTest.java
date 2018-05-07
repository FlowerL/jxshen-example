package com.jxshen.example.concurrent.visibility;

/**
 * Due to happens-before and memory barrier<br>
 * value1 is volatile so step2 happens before step3, that is step3 can see the result of step2 <br>
 * Also volatile prohibit reorder so step1 run before step3, step 4 run after step3. <br>
 * so step1 happens before step4. Finally v1 can't be bigger than v2 unless int overflow.
 *
 * @author jxshen on 2018/05/07
 */
public class VolatileTest {

    volatile int value1;
    int value2;

    public static void main(String[] args) {
        final VolatileTest main = new VolatileTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int value = 0;
                while (true) {
                    value++;
                    main.value2 = value; // step1
                    main.value1 = value; // step2
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int v1 = main.value1; // step3
                    int v2 = main.value2; // step4
                    if (v2 < v1) {
                        System.out.println(String.format("error v1=%d v2=%d", v1, v2));
                    }
                }
            }
        }).start();
    }
}
