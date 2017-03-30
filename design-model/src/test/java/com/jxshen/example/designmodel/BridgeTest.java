package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.bridge.AbstractNoodle;
import com.jxshen.example.designmodel.bridge.BeefNoodle;
import com.jxshen.example.designmodel.bridge.PepperyStyle;
import com.jxshen.example.designmodel.bridge.PlainStyle;
import com.jxshen.example.designmodel.bridge.PorkyNoodle;

public class BridgeTest {

    @Test
    public void doTest() {
        AbstractNoodle noodle1 = new BeefNoodle(new PepperyStyle());
        AbstractNoodle noodle2 = new BeefNoodle(new PlainStyle());
        AbstractNoodle noodle3 = new PorkyNoodle(new PepperyStyle());
        AbstractNoodle noodle4 = new PorkyNoodle(new PlainStyle());
        noodle1.eat();
        noodle2.eat();
        noodle3.eat();
        noodle4.eat();
    }
}
