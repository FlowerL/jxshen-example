package com.jxshen.example.lambda;

import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> pre = str -> str.length() == 4;
        pre = pre.and(p -> p.contains("f"));
        System.out.println(pre.test("12f4"));
    }
}
