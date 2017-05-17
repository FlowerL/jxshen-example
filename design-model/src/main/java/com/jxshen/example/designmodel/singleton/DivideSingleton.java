package com.jxshen.example.designmodel.singleton;

/**
 * reference from : http://blog.csdn.net/zhangerqing/article/details/8194653#comments<br>
 * this is a double-check lock since instance without synchronize can't<br>
 * guarantee safe reference
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
