package com.jxshen.example.jdk.classloader.selfdefine1;

/**
 * reference from: http://blog.csdn.net/chenjiazhan/article/details/37714401
 */
public class Printer implements IPrinter {
    @Override
    public void print() {
        System.out.println("Hello world!");
    }
}
