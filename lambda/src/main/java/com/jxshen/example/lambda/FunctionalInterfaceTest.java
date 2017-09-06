package com.jxshen.example.lambda;

public class FunctionalInterfaceTest {
    
    public static void main(String[] args) {
        B b = new B();
        b.inoke((String str) -> {
            System.out.println(str);
            return "hello " + str;
        });
        b.inoke((String str) -> "hello " + str);
        b.inoke(str -> "hello " + str);
        A a = String::valueOf;
        b.inoke(a);
        
        C c = new C();
        a = c::invSay;
        b.inoke(a);
        b.partVab();
        System.out.println("cname :" + b.cname + " sname: " + b.sname);
    }

}

@FunctionalInterface
interface A {
    String fLambda(String str);
    
    default void sayInt(int i) {
        System.out.println("sayInt--->" + i);
    }
    
    static void sayString(String str) {
        System.out.println("sayString--->" + str);
    }
}

interface A1 {
    String run();
}

class B {
    
    public String cname = "初始化的成员变量";
    static public String sname = "初始化的静态变量";
    
    public void inoke(A a) {
        System.out.println(a.fLambda("senssic"));
    }
    
    public void partVab() {
        String name = "local variable";
        this.inoke(str -> {
            cname = "修改后的成员变量";
            sname = "修改后的静态变量";
            return "hello " + str + " and " + name + " and " + cname + " and " + sname;
        });
        
        A1 a1 = new A1() {
            public String run() {
                cname = "被a1修改后的成员变量";
                sname = "被a1修改后的静态变量";
                System.out.println("cname :" + cname + " sname: " + sname);
                return "";
            }
        };
        a1.run();
    }
}

class C {
    public String invSay(String str) {
        return "world " + str;
    }
}
