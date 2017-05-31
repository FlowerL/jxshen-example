package com.jxshen.example.designmodel.templatemethod;

import com.jxshen.example.common.ReflectUtil;

public class ConcreteMethodObject1 extends TemplateMethodObject {

    @Override
    public void subImply() {
        System.out.println(String.format("Class[%s] Method[%s] called", 
                this.getClass().getSimpleName(),
                ReflectUtil.getCurrentMethodName()));
    }

    @Override
    public boolean isNeededHook() {
        return true;
    }

}
