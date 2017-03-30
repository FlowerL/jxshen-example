package com.jxshen.example.designmodel.singleton;

/**
 * reference from : http://blog.csdn.net/zhangerqing/article/details/8194653#comments
 */
public class DivideSingleton {

    private static DivideSingleton instance = null;
    
    private DivideSingleton() {}
    
    public static DivideSingleton getInstance() {
        if (instance == null) {
            syncInitial();
        }
        return instance;
    }
    
    public static synchronized void syncInitial() {
        if (instance == null) {
            instance = new DivideSingleton();
        }
    }
}
