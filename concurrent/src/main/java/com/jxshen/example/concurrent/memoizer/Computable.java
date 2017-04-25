package com.jxshen.example.concurrent.memoizer;

/**
 * reference from : Java Concurrent In Practice
 */
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}
