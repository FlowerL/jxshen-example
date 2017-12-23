package com.jxshen.example.designmodel.strategy.classloader;

public class ClassLoaderContext {

    private final Class m_caller;

    public Class getM_caller() {
        return m_caller;
    }

    public ClassLoaderContext(Class m_caller) {
        this.m_caller = m_caller;
    }
}
