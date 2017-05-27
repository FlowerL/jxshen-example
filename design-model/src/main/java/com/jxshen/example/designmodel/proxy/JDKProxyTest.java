package com.jxshen.example.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Integration for proxy model
 */
public class JDKProxyTest {

    // public interface for target and proxy
    public static interface Singable {
        void sing();
        void dance();
    }
    
    // target1
    public static class HighSinger implements Singable {
        @Override
        public void sing() {
            System.out.println("High Singer called to sing");
        }
        @Override
        public void dance() {
            System.out.println("High Singer called to dance");
        }
    }
    
    // target2
    public static class LowSinger implements Singable {
        @Override
        public void sing() {
            System.out.println("Low Singer called to sing");
        }
        @Override
        public void dance() {
            System.out.println("Low Singer called to dance");
        }
    }
    
    // target3
    public static class MiddleSinger implements Singable {
        @Override
        public void sing() {
            System.out.println("Middle Singer called to sing");
        }
        @Override
        public void dance() {
            System.out.println("Middle Singer called to dance");
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
            System.out.println("proxy Start");
            method.invoke(target, args);
            System.out.println("proxy End");
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
        
        // test if same class of target will get the only proxy
        Singable highSinger2 = new HighSinger();
        Singable highSingerProxy2 = ProxyFactory.newProxyInstance(highSinger2);
        
        System.out.println(highSingerProxy == highSingerProxy2);
        
        highSingerProxy.sing();
        lowSingerProxy.sing();
        middleSingerProxy.sing();
        
        highSingerProxy.dance();
        lowSingerProxy.dance();
        middleSingerProxy.dance();
    }
}
