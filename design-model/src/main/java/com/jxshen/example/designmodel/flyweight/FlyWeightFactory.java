package com.jxshen.example.designmodel.flyweight;

import java.util.Iterator;
import java.util.Vector;

/**
 * FlyWeight model
 * Reference from : http://blog.csdn.net/zhangerqing/article/details/8239539
 */
public class FlyWeightFactory {

    // for concurrent use vector
    private Vector<FlyWeightSubject> pool;
    
    // public parameter in FlyWeightSubject
    private static final String PUB_PARAM1 = "param1";
    private static final String PUB_PARAM2 = "param2";
    
    private int poolSize = 100;
    private static FlyWeightFactory instance = null;
    
    private FlyWeightFactory() {
        pool = new Vector<FlyWeightSubject>(poolSize);
        FlyWeightSubject flyWeightSubject = null;
        // initial pool
        for (int i = 0; i < poolSize; i ++) {
            flyWeightSubject = new FlyWeightSubject();
            flyWeightSubject.setPubParam1(PUB_PARAM1);
            flyWeightSubject.setPubParam2(PUB_PARAM2);
            
            pool.add(flyWeightSubject);
        }
    }
    
    public static FlyWeightFactory getFactory() {
        if (instance == null) {
            init();
        }
        return instance;
    }
    
    private static synchronized FlyWeightFactory init() {
        if (instance == null) {
            instance = new FlyWeightFactory();
        }
        return instance;
    }
    
    private static class FlyWeightFactoryHolder{
        public static FlyWeightFactory instance = new FlyWeightFactory();
    }
    
    public static FlyWeightFactory getInstance() {
        return FlyWeightFactoryHolder.instance;
    }
    
    public synchronized FlyWeightSubject getFlyWeightSubject() {
        Iterator<FlyWeightSubject> it = pool.iterator();
        if (it.hasNext()) {
            FlyWeightSubject returnSubject = it.next();
            it.remove();
            return returnSubject;
        }
        return null;
    }
    
    public synchronized void release(FlyWeightSubject flyWeightSubject) {
        pool.add(flyWeightSubject);
    }
    
}