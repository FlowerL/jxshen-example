package com.jxshen.example.jdk.thread;

public class LockAndNoLockContentTest {

    private String name = "initial";

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        LockAndNoLockContentTest testObj = new LockAndNoLockContentTest();
        new Thread(() -> {
            synchronized (testObj) {
                testObj.setName("write");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(testObj.getName());
        }).start();
    }


    private static class WriteLockObject {
        private String name = "initial";
    }

    public void accessPrivateFieldInInnerStaticObjectTest() {
        WriteLockObject writeLockObject = new WriteLockObject();
        writeLockObject.name = "sdfs";
    }
}
