package com.jxshen.example.concurrent.thread.factory;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private String poolName;
    
    public MyThreadFactory(String poolName) {
        super();
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }

}
