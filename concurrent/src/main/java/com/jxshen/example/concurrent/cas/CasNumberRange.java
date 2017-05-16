package com.jxshen.example.concurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * reference from: java concurrency in practice<br>
 * use cas, buzy loop and immutable object to ensure atomic instead of synchronized
 */
public class CasNumberRange {

    private static class IntPair {
        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
        final int lower;
        final int upper;
    }
    
    private final AtomicReference<IntPair> values =
            new AtomicReference<IntPair>(new IntPair(0, 0));
    
    public int getLower() { return values.get().lower; }
    public int getUpper() { return values.get().upper; }
    
    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i > oldv.upper) {
                throw new IllegalArgumentException(
                        "lower can't be set > upper");
            }
            IntPair newv = new IntPair(i, oldv.upper);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }
    
    public void setUpper(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i < oldv.lower) {
                throw new IllegalArgumentException(
                        "upper can't be set < lower");
            }
            IntPair newv = new IntPair(oldv.lower, i);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }
}
