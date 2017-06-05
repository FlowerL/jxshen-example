package com.jxshen.example.common;

import java.util.Base64;
import java.util.Base64.Encoder;

public class EncodeUtil {

    public static String Base64Encode(String src) {
        Encoder encoder = Base64.getUrlEncoder();
        return new String(encoder.encode(src.getBytes()));
    }
    
    public static void main(String[] args) {
        String s = "严";
        System.out.println(Base64Encode(s));
        s = "xu";
        System.out.println(Base64Encode(s));
        s = "x";
        System.out.println(Base64Encode(s));
    }
}
