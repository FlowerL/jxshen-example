package com.jxshen.example.designmodel.strategy.classloader;

public interface IClassLoaderStrategy {

    ClassLoader getClassLoader(ClassLoaderContext ctx);
}
