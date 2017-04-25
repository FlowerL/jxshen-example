package com.jxshen.example.concurrent.memoizer;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer<A, V> implements Computable<A, V> {

    private final ConcurrentHashMap<A, Future<V>> cache =
            new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;
    
    public Memorizer(Computable<A, V> c) {
        super();
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws InterruptedException {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
}
