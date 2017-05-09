package com.jxshen.example.concurrent.cas;

public class CasCounter {

    private SimulatedCAS value;
    
    public int getValue() {
        return value.get();
    }
    
    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSet(v, v+1));
        return v + 1;
    }
}
