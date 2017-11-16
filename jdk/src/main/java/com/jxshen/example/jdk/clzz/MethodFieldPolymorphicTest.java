package com.jxshen.example.jdk.clzz;

public class MethodFieldPolymorphicTest {

    public static void main(String[] args) {
        Father father = new Son();
        Son son = new Son();
        father.show();
        System.out.println("mainName =" + father.name);
        System.out.println("mainReflectName =" + father.reflectName);
        son.show();
        System.out.println("mainName =" + son.name);
        System.out.println("mainReflectName =" + son.reflectName);
    }

    private interface Interf {
        void show();
    }

    private static class Father implements Interf {
        private String name = "Father";
        private String reflectName = this.getClass().getSimpleName();
        @Override
        public void show() {
            System.out.println("name = " + name);
            System.out.println("reflectName = " + reflectName);
        }
    }

    private static class Son extends Father {
        private String name = "Son";
        private String reflectName = this.getClass().getSimpleName();
    }
}
