package com.jxshen.example.designmodel.proxy;

/**
 * Proxy Model<br>
 * Refernece from : http://blog.csdn.net/xsl1990/article/details/16359289
 */
public class GunPanther implements Panther {

    @Override
    public void info() {
        System.out.println("我是一只猎豹！");
    }

    @Override
    public void run() {
        System.out.println("我奔跑迅速");
    }

}
