package com.jxshen.example.concurrent.synchronizer;

/**
 * reference from: java concurrency in practice
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (super.isFull()) {
            throw new BufferFullException();
        }
        super.doPut(v);
    }
    
    public synchronized V take() throws BufferEmptyException {
        if (super.isEmpty()) {
            throw new BufferEmptyException();
        }
        return super.doTake();
    }
}
