package com.jxshen.example.designmodel.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibProxyTest {

    // target without interface
    public static class Singer {
        public void sing() {
            System.out.println("target sing");
        }
    }
    
    // Proxy
    public static class CGLibProxy implements MethodInterceptor {
        
        private Enhancer enhancer = new Enhancer();
        
        public Object getProxy(Class<?> clazz) {
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(this);
            return enhancer.create();
        }
        
        @Override
        public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3)
                throws Throwable {
            System.out.println("Proxy start");
            // all target call will be intercept so this place user proxy to call method to avoid dead loop
            arg3.invokeSuper(arg0, arg2);
            System.out.println("Proxy end");
            return null;
        }
    }
    
    public static void main(String[] args) {
        CGLibProxy proxy = new CGLibProxy();
        Singer singer = (Singer)proxy.getProxy(Singer.class);
        singer.sing();
    }
}
