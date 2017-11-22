package com.jxshen.example.jdk.lock;

import java.util.concurrent.locks.LockSupport;

public class ParkTest {

    public static void main(String[] args) {
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.print("block");
    }
}
