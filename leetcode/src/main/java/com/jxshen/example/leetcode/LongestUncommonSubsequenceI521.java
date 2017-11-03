package com.jxshen.example.leetcode;

public class LongestUncommonSubsequenceI521 {

    public int findLUSlength(String a, String b) {
        if (a == null && b == null) {
            return -1;
        }
        if (a == null) {
            return b.length();
        }
        if (b == null) {
            return a.length();
        }
        if (a == b || a.equalsIgnoreCase(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

}
