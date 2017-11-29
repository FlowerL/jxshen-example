package com.jxshen.example.leetcode;

public class RepeatedSubstringPattern459 {

    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        int length = s.length();
        for (int half = length / 2; half > 0; half--) {
            if (length % half == 0) {
                String subStr = s.substring(0, half);
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < length / half; i++) {
                    tmp.append(subStr);
                }
                if (tmp.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
