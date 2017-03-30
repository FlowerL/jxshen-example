package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.proxy.GunPanther;
import com.jxshen.example.designmodel.proxy.MyProxyFactory;
import com.jxshen.example.designmodel.proxy.Panther;

public class PantherProxyTest {

    @Test
    public void doTes() {
        Panther target = new GunPanther();
        Panther panther =  (Panther)MyProxyFactory.getProxy(target);
        
        panther.info();
        panther.run();
    }
}
