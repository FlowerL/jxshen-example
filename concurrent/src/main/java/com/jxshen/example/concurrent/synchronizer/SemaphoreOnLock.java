package com.jxshen.example.concurrent.synchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reference from: java concurrency in practice<br>
 * add tryAcquire & tryAcquire timeout version
 */
public class SemaphoreOnLock {

    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();
    private int permits;
    
    public SemaphoreOnLock(int permits) {
        lock.lock();
        try {
            this.permits = permits;
        } finally {
            lock.unlock();
        }
    }
    
    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
            return;
        } finally {
            lock.unlock();
        }
    }
    
    public boolean tryAcquire() {
        lock.lock();
        try {
            if (permits <= 0) {
                return false;
            }
            --permits;
            return true;
        } finally {
            lock.unlock();
        }
    }
    
    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        try {
            if (permits <= 0) {
                if (permitsAvailable.await(timeout, unit)) {
                    if (permits <= 0) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            --permits;
            return true;
        } finally {
            lock.unlock();
        }
    }
    
    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
