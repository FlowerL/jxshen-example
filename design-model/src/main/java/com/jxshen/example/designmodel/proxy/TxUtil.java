package com.jxshen.example.designmodel.proxy;

/**
 * Proxy Model<br>
 * mock transaction in proxy model<br>
 * Refernece from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class TxUtil {

    public void beginTx() {
        System.out.println("=====模拟开始事务=====");
    }
    
    public void endTx() {
        System.out.println("=====模拟结束事务=====");
    }
}
