package com.jxshen.example.concurrent.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * reference from : java concurrency in practice
 */
public class MyAppThread extends Thread {

    public static final String DEFAULT_NAME = "MyAppThread";
    public static volatile boolean debugLifeCycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();
    
    public MyAppThread(Runnable r) {this(r, DEFAULT_NAME);}
    
    public MyAppThread(Runnable runnable, String name) {
        super(runnable, name + "-" + created.incrementAndGet());
        super.setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        log.log(Level.SEVERE, 
                                "UNCAUGHT in thread " + t.getName(), e);
                    }
                });
    }
    
    public void run() {
        boolean debug = debugLifeCycle;
        if (debug) {
            log.log(Level.FINE, "Created " + super.getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exiting " + super.getName());
            }
        }
    }
    
}
