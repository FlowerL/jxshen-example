package com.jxshen.example.concurrent;

public class TestSpinLock {

    private volatile int value;
    
    static final long spinForTimeoutThreshold = 1000L;
    
    public final int get() {
        return value;
    }
    
    public final int incrementAndGet() throws Exception {
        for(Long i = 0L; i < spinForTimeoutThreshold; i++) {
            int current = get();
            int addInt = value + 1;
            if (compareAndSet(current, addInt)) {
                return addInt;
            }
        }
        throw new Exception("spin number exhaust");
    }
    
    private final boolean compareAndSet(int expect, int modify) {
        return false;
    }
}
