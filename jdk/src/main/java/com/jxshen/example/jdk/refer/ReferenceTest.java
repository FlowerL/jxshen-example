package com.jxshen.example.jdk.refer;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * reference from: http://blog.csdn.net/androidstar_cn/article/details/54710652
 */
public class ReferenceTest {

    private static List<Reference> roots = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue rq = new ReferenceQueue();
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    Reference r = rq.remove();
                    System.out.println("reference:" + r);
                    System.out.println("get:" + r.get());
                    i++;
                    System.out.println("queue remove num: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 100000; i++) {
            byte[] a = new byte[1024 * 1024];
            Reference r = new SoftReference(a, rq);
//            Reference r = new WeakReference(a, rq);
//            Reference r = new PhantomReference(a, rq);
            roots.add(r);
            System.gc();

            System.out.println("produce: " + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
