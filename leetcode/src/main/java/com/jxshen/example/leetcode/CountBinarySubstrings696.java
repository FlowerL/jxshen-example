package com.jxshen.example.leetcode;

public class CountBinarySubstrings696 {

    public int countBinarySubstrings(String s) {
        int res = 0;
        if (s == null || s.length() < 2) {
            return res;
        }
        int preCount = 0;
        int curCount = 1;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i-1]) {
                preCount = --curCount;
                curCount = 1;
                res++;
                continue;
            }
            curCount++;
            if (--preCount >= 0) {
                res++;
            }
        }
        return res;
    }
}
