package com.jxshen.example.lambda;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.ofNullable(null);
        System.out.println(optional.isPresent());
//        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));
        
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }
}
