package com.jxshen.example.designmodel.factory;

public class DebugWorkFactory implements AbstractWorkFactory {

    @Override
    public WorkInterf getWork() {
        return new DebugWork();
    }

}
