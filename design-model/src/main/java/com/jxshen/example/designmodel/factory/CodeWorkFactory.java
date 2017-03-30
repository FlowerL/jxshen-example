package com.jxshen.example.designmodel.factory;

public class CodeWorkFactory implements AbstractWorkFactory {

    @Override
    public WorkInterf getWork() {
        return new CodeWork();
    }

}
