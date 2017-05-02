package com.jxshen.example.concurrent.map;

/**
 * reference from: java concurrency in practice<br>
 * demo of concurrentHashMap
 */
public class StripedMap {

    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;
    
    private static class Node {
        private Object key;
        private Object value;
        public Node next() {return null;}
    }
    
    public StripedMap(int numBuckets) {
        if (numBuckets <= 0) {
            numBuckets = N_LOCKS;
        }
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i< N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }
    
    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
    
    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next()) {
                if (m.key.equals(key)) {
                    return m.value;
                }
            }
        }
        return null;
    }
    
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }
    
    
}
