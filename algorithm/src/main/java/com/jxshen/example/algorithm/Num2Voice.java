package com.jxshen.example.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Num2Voice {

    public static final Map<Character, String> NUM;
    static {
        NUM = new HashMap<Character, String>();
        NUM.put('0', "零");
        NUM.put('1', "一");
        NUM.put('2', "二");
        NUM.put('3', "三");
        NUM.put('4', "四");
        NUM.put('5', "五");
        NUM.put('6', "六");
        NUM.put('7', "七");
        NUM.put('8', "八");
        NUM.put('9', "九");
    }
    
    public static final Map<Integer, String> BASIC;
    static {
        BASIC = new HashMap<Integer, String>();
        BASIC.put(1, "十");
        BASIC.put(2, "百");
        BASIC.put(3, "千");
    }
    
    public static final Map<Integer, String> CARRY;
    static {
        CARRY = new HashMap<Integer, String>();
        CARRY.put(0, "");
        CARRY.put(1, "万");
        CARRY.put(2, "亿");
        CARRY.put(3, "兆");
    }
    
    private static final class Digit {
        public Integer basic;
        public Integer carry;
        
        public Digit(Integer length) {
            basic = (length-1) % 4;
            carry = (length-1) / 4;
        }
        
        public void countDown() {
            if (basic == 0) {
                basic = 3;
                carry--;
            } else {
                basic--;
            }
        }
    }
    
    public static String num2Voice(int num) {
        StringBuilder result = new StringBuilder();
        String numStr = new Integer(num).toString();
        
        // 正负
        if (num < 0) {
            result.append("负");
            numStr = numStr.substring(1);
        }
        
        Integer length = numStr.length();
        Digit digit = new Digit(length);
        
        for (int i = 0; i < length; i++) {
            // 处理0
            char ch = numStr.charAt(i);
            if (ch == '0') {
                String preStr = result.substring(result.length()-1);
                if ("零".equals(preStr) || CARRY.containsValue(preStr)) {
                    digit.countDown();
                    continue;
                }
                if (digit.basic == 0) {
                    result.append(CARRY.get(digit.carry));
                }
                else {
                    result.append("零");
                }
            }
            else {
                result.append(NUM.get(ch));
                if (digit.basic == 0) {
                    result.append(CARRY.get(digit.carry));
                }
                else {
                    result.append(BASIC.get(digit.basic));
                }
            }
            digit.countDown();
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(num2Voice(100000));
    }
}
