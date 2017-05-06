package com.jxshen.example.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TimeOutLock {
    
    private Lock lock;

    public boolean trySendOnSharedLine(String msg, long timeout, TimeUnit unit) throws InterruptedException {
        if (!lock.tryLock(timeout, unit)) {
            return false;
        }
        try {
            return sendOnSharedLine(msg);
        } finally {
            lock.unlock();
        }
    }

    private boolean sendOnSharedLine(String msg) {
        // TODO Auto-generated method stub
        return false;
    }
}
