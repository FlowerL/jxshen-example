package com.jxshen.example.mock;

import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * reference from: http://blog.csdn.net/jamesdoctor/article/details/50019103
 */
public class MyMockito {

    public static final Map<MethodInfo, Object> MOCKED_METHODS = new HashMap<>();

    public static MockInjector when(Object methodCall) {
        return new MockInjector((MethodInfo)methodCall);
    }

    private static MyCGLibInterceptor getInterceptor() {
        return new MyCGLibInterceptor();
    }

    public static void main(String[] args) {
        final List myMockList1 = getInterceptor().getInstance(List.class);
        final List myMockList2 = getInterceptor().getInstance(List.class);
        final Map myMockMap = getInterceptor().getInstance(Map.class);
        MyMockito.when(myMockList1.get(0)).thenReturn("Hello, I am James");
        MyMockito.when(myMockList1.get(2)).thenReturn("Hello, I am Billy");
        MyMockito.when(myMockList2.get(0)).thenReturn("Hello, I am Tom");
        MyMockito.when(myMockMap.get(10)).thenReturn("Hello, I am Bob");

        System.out.println("myMockList1.get(0) = " + myMockList1.get(0));
        System.out.println("myMockList1.get(2) = " + myMockList1.get(2));
        System.out.println("myMockList2.get(0) = " + myMockList2.get(0));
        System.out.println("myMockMap.get(10) = " + myMockMap.get(10));
    }
}

class MyCGLibInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        final MethodInfo key = new MethodInfo(this, method, args);
        final boolean hasMocked = MyMockito.MOCKED_METHODS.containsKey(key);
        if (!hasMocked) {
            System.out.println("Initializing the mock for " + key.toString());
            return key;
        }
        else {
            System.out.println("Returns the mock result:");
            return MyMockito.MOCKED_METHODS.get(key);
        }
    }

    public <T> T getInstance(final Class<T> t) {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(this);
        return t.cast(enhancer.create());
    }
}

@Data
class MethodInfo {
    private final MyCGLibInterceptor interceptor;
    private final Method method;
    private final Object[] args;

    @Override
    public String toString() {
        return "{interceptor: " + interceptor + ", Method: " + method + ", args: " + Arrays.toString(args) + "}";
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof MethodInfo) {
            final MethodInfo otherMethodInfo = (MethodInfo) other;
            return interceptor.equals(otherMethodInfo.interceptor)
                    && method.equals(otherMethodInfo.method)
                    && Arrays.equals(args, otherMethodInfo.args);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return interceptor.hashCode() + method.hashCode() + Arrays.hashCode(args);
    }
}

@Data
class MockInjector {
    private final MethodInfo methodInfo;
    public void thenReturn(final Object mockResult) {
        MyMockito.MOCKED_METHODS.put(methodInfo, mockResult);
    }
}
