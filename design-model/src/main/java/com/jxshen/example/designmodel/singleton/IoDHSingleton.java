package com.jxshen.example.designmodel.singleton;

public class IoDHSingleton {
    
    private IoDHSingleton() {}

    private static class LazyInstanceHolder {
        private static final IoDHSingleton  instance = new IoDHSingleton();
    }
    
    public static IoDHSingleton getInstance() {
        return LazyInstanceHolder.instance;
    }
}
