package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (s == null || s.isEmpty() || p == null || p.isEmpty() || s.length() < p.length()) {
            return res;
        }

        int[] hash = new int[128];
        for (char c : p.toCharArray()) {
            hash[c]++;
        }

        int count = p.length();
        int begin = 0;
        int end = 0;
        while (end < s.length()) {
            if (hash[s.charAt(end++)]-- >= 1)
                count--;
            if (count == 0)
                res.add(begin);
            if (end - begin == p.length() && hash[s.charAt(begin++)]++ >= 0)
                count++;
        }
        return res;
    }
}
