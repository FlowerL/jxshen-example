package com.jxshen.example.leetcode;

public class ReverseStringII541 {

    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 8));
    }

    public static String reverseStr(String s, int k) {
        if (s == null || s.isEmpty()) {
            return new String();
        }
        if (k <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int half = (k - 1) / 2; // 长度取中间，长度要减1
        int mutliCount = chars.length / k + (chars.length % k == 0 ? 0 : 1);
        for (int i = 0; i < mutliCount; i += 2) {
            if ((i + 1) * k <= chars.length) {
                for (int j = 0; j <= half; j++) { // 循环的下标只从0开始，具体的位置在逻辑里算
                    swap(chars, i * k + j, (i + 1) * k - j - 1);
                }
            }
            if ((i + 1) * k > chars.length) {
                int lastHalf = (chars.length - i * k - 1) / 2;
                for (int j = 0; j <= lastHalf; j++) {
                    swap(chars, i * k + j, chars.length - 1 - j);
                }
            }
        }
        return new String(chars);
    }

    private static void swap(char[] chars, int i, int j) {
        if (i == j) {
            return;
        }
        chars[i] = (char)(chars[i] ^ chars[j]);
        chars[j] = (char)(chars[i] ^ chars[j]);
        chars[i] = (char)(chars[i] ^ chars[j]);
    }

}
