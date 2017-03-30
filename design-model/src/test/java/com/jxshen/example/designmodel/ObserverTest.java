package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.observer.NameObserver;
import com.jxshen.example.designmodel.observer.PriceObserver;
import com.jxshen.example.designmodel.observer.ProductImpl;

public class ObserverTest {

    @Test
    public void doTest() {
        ProductImpl tv = new ProductImpl("电视机", 175);
        
        NameObserver no = new NameObserver();
        PriceObserver po = new PriceObserver();
        
        tv.registObserver(po);
        tv.registObserver(no);
        
        tv.setName("海尔");
        tv.setPrice(200);
    }
}
