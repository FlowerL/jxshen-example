package com.jxshen.example.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Integration for proxy model
 */
public class ProxyModelTest {

    // public interface for target and proxy
    public static interface Singable {
        void sing();
    }
    
    // target1
    public static class HighSinger implements Singable {
        @Override
        public void sing() {
            System.out.println("High Singer called to sing");
        }
    }
    
    // target2
    public static class LowSinger implements Singable {
        @Override
        public void sing() {
            System.out.println("Low Singer called to sing");
        }
    }
    
    // target3
    public static class MiddleSinger implements Singable {
        @Override
        public void sing() {
            System.out.println("Middle Singer called to sing");
        }
    }
    
    // InvocationHandler
    public static class SingHandler implements InvocationHandler {
        private Object target;
        
        public SingHandler(Object target) {
            super();
            this.target = target;
        }

        public Object getTarget() {
            return target;
        }

        public void setTarget(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("SingHandler Called Start");
            method.invoke(target, args);
            System.out.println("SingHandler Called End");
            return null;
        }
    }
    
    public static class ProxyFactory {
        @SuppressWarnings("unchecked")
        public static <T> T newProxyInstance(T target) {
            Class<?> clazz = target.getClass();
            InvocationHandler h = new SingHandler(target);
            return (T) Proxy.newProxyInstance(clazz.getClassLoader(), 
                    clazz.getInterfaces(), h);
        }
    }
    
    public static void main(String[] args) {
        Singable highSinger = new HighSinger();
        Singable lowSinger = new LowSinger();
        Singable middleSinger = new MiddleSinger();
        
        Singable highSingerProxy = ProxyFactory.newProxyInstance(highSinger);
        Singable lowSingerProxy = ProxyFactory.newProxyInstance(lowSinger);
        Singable middleSingerProxy = ProxyFactory.newProxyInstance(middleSinger);
        
        highSingerProxy.sing();
        lowSingerProxy.sing();
        middleSingerProxy.sing();
    }
}
