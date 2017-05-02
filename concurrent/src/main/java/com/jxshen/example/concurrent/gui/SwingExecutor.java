package com.jxshen.example.concurrent.gui;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * reference from: java concurrency in practice;
 */
public class SwingExecutor {

    private static final ExecutorService exec = 
            Executors.newSingleThreadExecutor(new SwingThreadFactory());
    private static volatile Thread swingThread;
    
    private static class SwingThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }
    
    public static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }
    
    public static void invokeLater(Runnable task) {
        exec.execute(task);
    }
    
    public static void invokeAndWait(Runnable task) throws InterruptedException {
        Future f = exec.submit(task);
        try {
            f.get();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
