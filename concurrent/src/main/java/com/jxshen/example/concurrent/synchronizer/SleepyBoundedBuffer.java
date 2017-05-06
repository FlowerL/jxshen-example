package com.jxshen.example.concurrent.synchronizer;

/**
 * reference from: java concurrency in practice
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private static final int SLEEP_GRANULARITY = 1000;
    
    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }
    
    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!super.isFull()) {
                    super.doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
    
    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!super.isEmpty()) {
                    return super.doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

}
