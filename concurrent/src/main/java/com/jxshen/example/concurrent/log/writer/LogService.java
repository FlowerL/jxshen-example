package com.jxshen.example.concurrent.log.writer;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * reference from : java concurrency in practice<br>
 * reliable version
 */
public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    private boolean isShutdown;
    private int reservations;
    
    public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer,
            boolean isShutdown, int reservations) {
        super();
        this.queue = queue;
        this.loggerThread = loggerThread;
        this.writer = writer;
        this.isShutdown = isShutdown;
        this.reservations = reservations;
    }

    public void start() {loggerThread.start();}
    
    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }
    
    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("logService is already shutdown");
            }
            ++reservations;
        }
        queue.put(msg);
    }
    
    private class LoggerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } 
            } finally {
                writer.close();
            }
        }
    }
}
