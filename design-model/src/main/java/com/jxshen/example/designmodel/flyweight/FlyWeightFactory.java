package com.jxshen.example.designmodel.flyweight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * FlyWeight model
 * Reference from : http://blog.csdn.net/zhangerqing/article/details/8239539
 */
public class FlyWeightFactory {

    private List<FlyWeightSubject> pool;
    
    // public parameter in FlyWeightSubject
    private static final String PUB_PARAM1 = "param1";
    private static final String PUB_PARAM2 = "param2";
    
    private int poolSize = 100;
    FlyWeightSubject flyWeightSubject = null;
    
    private FlyWeightFactory() {
        pool = new ArrayList<FlyWeightSubject>(poolSize);
        // initial pool
        for (int i = 0; i < poolSize; i ++) {
            flyWeightSubject = new FlyWeightSubject();
            flyWeightSubject.setPubParam1(PUB_PARAM1);
            flyWeightSubject.setPubParam2(PUB_PARAM2);
            
            pool.add(flyWeightSubject);
        }
    }
    
    public synchronized static FlyWeightFactory getFactory() {
        return new FlyWeightFactory();
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
    
    public synchronized void release() {
        pool.add(flyWeightSubject);
    }
    
}