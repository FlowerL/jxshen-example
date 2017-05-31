package com.jxshen.example.designmodel;

import org.junit.Test;

import com.jxshen.example.designmodel.templatemethod.ConcreteMethodObject1;
import com.jxshen.example.designmodel.templatemethod.ConcreteMethodObject2;
import com.jxshen.example.designmodel.templatemethod.TemplateMethodObject;

public class TemplateMethodTest {

    @Test
    public void doTest() {
        TemplateMethodObject method1 = new ConcreteMethodObject1();
        TemplateMethodObject method2 = new ConcreteMethodObject2();
        method1.templateMethod();
        method2.templateMethod();
    }
}
