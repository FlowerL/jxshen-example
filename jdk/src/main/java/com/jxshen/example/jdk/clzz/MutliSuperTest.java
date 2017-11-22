package com.jxshen.example.jdk.clzz;

public class MutliSuperTest {

    private static class Super1 {
        private String name = "Super1";
        public void show() {
            System.out.println(name);
        }
    }

    private static class Super2 extends Super1 {
        private String name = "Super2";
        public final void show() {
            System.out.println(name);
        }
    }

    private static class Super3 extends Super2 {
        private String name = "Super3";
    }

    public static void main(String[] args) {
        Super1 s1 = new Super1();
        s1.show();
        Super1 s2 = new Super2();
        s2.show();
        Super1 s3 = new Super3();
        s3.show();
    }
}
