package com.jxshen.example.lambda;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args) {
        List<String> sList = new ArrayList<String>();  
        sList.add("a1");  
        sList.add("b2");  
        sList.add("ab");  
        sList.add("acc");  
        sList.add("bc");  
        sList.add("cf6");  
        sList.add("a7");  
        sList.add("17");  
        sList.add("236");  
        sList.add("589");  
        sList.add("5898955");  
        
        sList.stream().filter(str -> str.length() == 3).filter(str -> str.contains("c")).forEach(System.out::println);
        sList.stream().filter(str -> str.contains("c")).sorted((str1, str2) -> {
            if (str1.length() == str2.length()) {
                return 0;
            } else if (str1.length() > str2.length()) {
                return 1;
            } else {
                return -1;
            }
        }).forEach(System.out::println);
        sList.stream().filter(str -> str.matches("\\d+")).map(str -> Integer.parseInt(str)).forEach(System.out::println);
    }
}
