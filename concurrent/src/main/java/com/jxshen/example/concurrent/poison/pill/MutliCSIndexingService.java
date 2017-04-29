package com.jxshen.example.concurrent.poison.pill;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * reference from : java concurrency in practice<br>
 * using mutli producers and consumers as expansion
 */
public class MutliCSIndexingService {

    private static final File POISON = new File("");
    private AtomicInteger number;
    private final List<IndexerThread> consumers = new ArrayList<IndexerThread>();
    private final List<CrawlerThread> producers = new ArrayList<CrawlerThread>();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;
    
    public MutliCSIndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        super();
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }
    
    public void start() {
        for (CrawlerThread producer : producers) {
            producer.start();
        }
        for (IndexerThread consumer : consumers) {
            consumer.start();
        }
    }
    
    public void stop() {
        for (CrawlerThread producer : producers) {
            producer.interrupt();
        }
    }
    
    public void awaitTermination() throws InterruptedException {
        for (IndexerThread consumer : consumers) {
            consumer.join();
        }
    }

    private class CrawlerThread extends Thread {
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        number.incrementAndGet();
                        break;
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        
        private void crawl(File root) throws InterruptedException {}
    }
    
    private class IndexerThread extends Thread {
        public void run () {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON && number.decrementAndGet() == 0) {
                        break;
                    } else {
                        index(file);
                    }
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        private void index(File root) throws InterruptedException {}
    }
}
