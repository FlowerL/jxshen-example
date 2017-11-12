package com.jxshen.example.jdk.clzz;

public class SonA extends FatherA {
    @Override
    public void execute() {
        System.out.println("SonA");
    }

    public static void main(String[] args) {
        SonA sonA = new SonA();
        FatherA fatherA = sonA;
        fatherA.execute();
        FatherA fatherA1 = new FatherA();
        fatherA1.execute();
    }
}
