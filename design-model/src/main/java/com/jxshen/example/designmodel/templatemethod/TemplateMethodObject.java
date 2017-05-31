package com.jxshen.example.designmodel.templatemethod;

import com.jxshen.example.common.ReflectUtil;

/**
 * Template Method define the procedure of a method with some of implemention
 * subclass can concrete left method and hook method
 * 
 * @author jxshen
 *
 */
public abstract class TemplateMethodObject {

    public void templateMethod() {
        operate1();
        subImply();
        if (isNeededHook()) {
            operate2();
        }
        operateHook();
    }
    
    private void operate1(){
        System.out.println(String.format("Class[%s] Method[%s] called", 
                this.getClass().getSimpleName(),
                ReflectUtil.getCurrentMethodName()));
    }
    
    private void operate2(){
        System.out.println(String.format("Class[%s] Method[%s] called", 
                this.getClass().getSimpleName(),
                ReflectUtil.getCurrentMethodName()));
    }
    
    protected abstract void subImply();
    
    // 钩子方法1：子类实现的钩子方法，决定是否运行算法某部分
    protected abstract boolean isNeededHook();
    
    // 钩子方法2：父类给出空实现的钩子方法，子类可以覆盖也可以不管。
    protected void operateHook() {}
    
}
