package com.jxshen.example.concurrent.cas;

/**
 * reference from: java concurrency in practice
 */
public class SimulatedCAS {

    private int value;
    
    public synchronized int get() {return value;}
    
    public synchronized int compareAndSet(int expectValue, int newValue) {
        int oldValue = value;
        if (value == expectValue) {
            value = newValue;
        }
        return oldValue;
    }
    
    public synchronized boolean comareAndSet(int expectValue, int newValue) {
        return expectValue == compareAndSet(expectValue, newValue);
    }
}
