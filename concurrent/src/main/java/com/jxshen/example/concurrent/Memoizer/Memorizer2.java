package com.jxshen.example.concurrent.Memoizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * reference from : Java Concurrency In Practice.<br>
 * use ConcurrentHashMap as cache to improve concurrency.<br>
 * problem happens when two thread concurrent get the same arg when cache doesn't hold
 * @param <A>
 * @param <V>
 */
public class Memorizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;
    
    public Memorizer2(Computable<A, V> c) {
        super();
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
            
        }
        return result;
    }
    
    
}
