package com.jxshen.example.designmodel.singleton;

/**
 * reference from: java concurrency in practice<br>
 * this is not double-checking lock since it uses volatile to<br>
 * guarantee visibility.
 */
public class LazySingleton {

    private volatile static LazySingleton instance = null;
    
    private LazySingleton() {}
    
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized(LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
