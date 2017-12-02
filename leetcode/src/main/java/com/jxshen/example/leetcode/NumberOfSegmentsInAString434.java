package com.jxshen.example.leetcode;

public class NumberOfSegmentsInAString434 {

    public int countSegments(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int res = 0;
        char pre = ' ';
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c != ' ' && pre == ' ') {
                res++;
                pre = c;
            }
            else if (c == ' ' && pre != ' ') {
                pre = c;
            }
        }
        return res;
    }
}
