package com.jxshen.example.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Proxy Model<br>
 * enhance proxy function<br>
 * Refernece from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;
    
    public Object getTarget() {
        return target;
    }


    public void setTarget(Object target) {
        this.target = target;
    }

    // every function executed from target will be replaced by this method
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TxUtil tx = new TxUtil();
        tx.beginTx();
        Object result = method.invoke(target, args);
        tx.endTx();
        return result;
    }

}
