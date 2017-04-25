package com.jxshen.example.concurrent.memoizer;

import java.util.HashMap;
import java.util.Map;

/**
 * reference from : Java Concurrency In Practice.<br>
 * use HashMap as cache with synchronized to guard thread safe.<br>
 * weak concurrency
 * @param <A>
 * @param <V>
 */
public class Memorizer1<A, V> implements Computable<A, V> {
    
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;
    public Memorizer1(Computable<A, V> c) {
        this.c = c;
    }
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
    
    
}
