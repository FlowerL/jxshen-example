package com.jxshen.example.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * reference from : Java Concurrency In Practice
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore sem;
    
    public BoundedHashSet(int bound) {
        if (bound < 1) {
            throw new RuntimeException("bound can't be less than 1");
        }
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }
    
    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean isAdded = false;
        try {
            isAdded = set.add(o);
            return isAdded;
        } finally {
            if (!isAdded) {
                sem.release();
            }
        }
    }
    
    public boolean remove(T o) {
        boolean isRemoved = false;
        try {
            isRemoved = set.remove(o);
            return isRemoved;
        } finally {
            if (isRemoved) {
                sem.release();
            }
        }
    } 
}
