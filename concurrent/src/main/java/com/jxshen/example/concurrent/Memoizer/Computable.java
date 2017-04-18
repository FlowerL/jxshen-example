package com.jxshen.example.concurrent.Memoizer;

/**
 * reference from : Java Concurrent In Practice
 */
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;
}
