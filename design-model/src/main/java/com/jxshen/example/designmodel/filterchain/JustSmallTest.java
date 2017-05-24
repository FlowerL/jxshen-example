package com.jxshen.example.designmodel.filterchain;

public class JustSmallTest {

    public static void main(String[] args) {
        String str = "just <> test";
        String after = process(str);
        System.out.println(str);
        System.out.println(after);
    }
    
    public static String process(String str) {
        return str.replace("<", "&lt;");
    }
}
