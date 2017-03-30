package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.chain.MyHandler;

public class ChainTest {

    @Test
    public void doTest() {
        MyHandler h1 = new MyHandler("h1");
        MyHandler h2 = new MyHandler("h2");
        MyHandler h3 = new MyHandler("h3");
        MyHandler h4 = new MyHandler("h4");
        
        h1.setHandler(h2);
        h2.setHandler(h3);
        h3.setHandler(h4);
        
        h1.operate();
    }
}
