package com.jxshen.example.concurrent.synchronizer;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * original<br>
 * use one signal semaphore to build ReentrantLock
 */
public class ReentrantLockOnSemaphore {

    private final Semaphore semaphore = new Semaphore(1);
    
    public void lock() {
        for (;;) {
            try {
                semaphore.acquire();
                return;
            } catch (InterruptedException e) {
            }
        }
    }
    
    public boolean tryLock() {
        if (semaphore.tryAcquire()) {
            return true;
        }
        return false;
    }
    
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        if (semaphore.tryAcquire(timeout, unit)) {
            return true;
        }
        return false;
    }
    
    public void unlock() {
        semaphore.release();
    }
}
