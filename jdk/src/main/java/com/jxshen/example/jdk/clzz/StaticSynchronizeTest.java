package com.jxshen.example.jdk.clzz;

public class StaticSynchronizeTest {

    private interface interf {
        void doFun();
        default void do2() {
            synchronized (this) {

            }
        }
    }

    private static class Father implements interf {
        @Override
        public synchronized void doFun() {

        }
    }
}
