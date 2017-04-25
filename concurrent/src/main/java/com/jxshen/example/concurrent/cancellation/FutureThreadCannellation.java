package com.jxshen.example.concurrent.cancellation;

import java.util.concurrent.TimeUnit;

public class FutureThreadCannellation {

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;
            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }
            public void rethrow() {
                if (t != null) {
                    throw launderThrowable(t);
                }
            }
        }
        
        RethrowableTask task = new RethrowableTask();
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
