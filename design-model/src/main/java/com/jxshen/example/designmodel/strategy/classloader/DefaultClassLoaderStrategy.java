package com.jxshen.example.designmodel.strategy.classloader;

public class DefaultClassLoaderStrategy implements IClassLoaderStrategy {

    @Override
    public ClassLoader getClassLoader(ClassLoaderContext ctx) {
        final ClassLoader callerLoader = ctx.getM_caller().getClassLoader();
        final ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        final ClassLoader systemLoader = ClassLoader.getSystemClassLoader();

        ClassLoader resultLoader = null;
        if (isChild(contextLoader, callerLoader)) {
            resultLoader = callerLoader;
        }
        else if (isChild(callerLoader, contextLoader)) {
            resultLoader = contextLoader;
        }
        else {
            resultLoader = contextLoader;
        }

        if (isChild(resultLoader, systemLoader)) {
            resultLoader = systemLoader;
        }
        return resultLoader;
    }

    private boolean isChild(ClassLoader parent, ClassLoader child) {
        return parent.getParent().equals(child);
    }
}
