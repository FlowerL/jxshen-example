package com.jxshen.example.leetcode;

public class RepeatedStringMatch686 {

    public int repeatedStringMatch(String A, String B) {
        if (A == null || A.isEmpty() || B == null || B.isEmpty()) {
            return -1;
        }

        StringBuilder sb = new StringBuilder(A);
        int res = 1;
        while (sb.length() < B.length()) {
            sb.append(A);
            res++;
        }

        if (sb.indexOf(B) >= 0) {
            return res;
        }
        sb.append(A);
        res++;
        if (sb.indexOf(B) >= 0) {
            return res;
        }
        return -1;

    }
}
