package com.jxshen.example.common;

public class InnerClassTest {
    
    public static int age = 1;
    
    public static class InnerStaticClass {}
    class InnerConcreteClass {}
    
    public void concrete() {
        final class LocalFinalClass {};
        class LocalClass {};
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                age = -1;
            }
        }).start();
    }
    
    public void concrete2() {
        final class LocalFinalClass {};
        class LocalClass {};
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                age = -1;
            }
        }).start();
    }
    
    public static void main(String[] args) throws InterruptedException {
        final class LocalFinalClass {};
        class LocalClass {};
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                age = -1;
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                age = -1;
            }
        }).start();
        Thread.sleep(1000);
        System.out.println(age);
    }

}

class ParallelClass {}