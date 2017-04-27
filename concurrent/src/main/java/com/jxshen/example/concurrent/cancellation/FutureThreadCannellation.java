package com.jxshen.example.concurrent.cancellation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureThreadCannellation {
    
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();
    
    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
    }
    
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not checked", t);
        }
    }
}
