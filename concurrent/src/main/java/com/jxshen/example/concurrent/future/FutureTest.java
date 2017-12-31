package com.jxshen.example.concurrent.future;

import java.util.concurrent.*;

/**
 * referenfe from: http://ifeve.com/线程池使用futuretask时候需要注意的一点事/
 */
public class FutureTest {

    //(1)线程池单个线程，线程池队列元素个数为1
    private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // (2)添加任务one
        Future futureOne = executorService.submit(() -> {
            System.out.println("start runnable one");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // (3)添加任务two
        Future futureTwo = executorService.submit(() -> {
            System.out.println("start runnable two");
        });

        // (4)添加任务three
        Future futureThree = null;
        try {
            futureThree = executorService.submit(() -> {
                System.out.println("start runnable three");
            });
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println("task one " + futureOne.get());//(5)等待任务one执行完毕
        System.out.println("task two " + futureTwo.get());//(6)等待任务two执行完毕
        System.out.println("task three " + (futureThree==null ? null : futureThree.get()));// (7)等待任务three执行完毕

        executorService.shutdown();//(8)关闭线程池，阻塞直到所有任务执行完毕
    }
}
