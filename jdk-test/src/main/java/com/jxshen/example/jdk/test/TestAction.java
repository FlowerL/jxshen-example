package com.jxshen.example.jdk.test;

import com.jxshen.example.jdk.classloader.ActionInterface;

public class TestAction implements ActionInterface {
    @Override
    public String action() {
        return "com.jxshen.example.jdk.test.TestAction";
    }
}
