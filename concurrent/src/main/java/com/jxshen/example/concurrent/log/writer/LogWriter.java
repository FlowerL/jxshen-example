package com.jxshen.example.concurrent.log.writer;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * reference from : java concurrency in practice<br>
 * unreliable service
 */
public class LogWriter {

    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private volatile boolean shutdownRequested;
    
    public LogWriter(PrintWriter writer) {
        this.queue = new LinkedBlockingQueue<String>();
        this.logger = new LoggerThread(writer);
    }
    
    public void start() {
        logger.start();
    }
    
    /**
     * Unreliable log shutdown
     * @param msg
     * @throws InterruptedException
     */
    public void log(String msg) throws InterruptedException {
        if (!shutdownRequested) {
            queue.put(msg);
        } else {
            throw new IllegalStateException("logger is shut down");
        }
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;
        
        public LoggerThread(PrintWriter writer) {
            super();
            this.writer = writer;
        }

        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }
}
