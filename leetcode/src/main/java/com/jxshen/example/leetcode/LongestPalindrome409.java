package com.jxshen.example.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindrome409 {

    public int longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int res = 0;
        for (char c : chars) {
            if (set.remove(c)) {
                res = res + 2;
            }
            else {
                set.add(c);
            }
        }
        if (set.size() > 0) {
            res++;
        }
        return res;
    }
}
