package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FindTheDifference389 {

    public char findTheDifference(String s, String t) {
        if (s == null || s.isEmpty()) {
            return t.charAt(0);
        }

        char[] sChars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : sChars) {
            Integer count = map.get(c);
            if (count == null) {
                count = 0;
            }
            map.put(c, ++count);
        }
        char[] tChars = t.toCharArray();
        for (Character c : tChars) {
            Integer count = map.get(c);
            if (count == null || --count < 0) {
                return c;
            }
            map.put(c, count);
        }
        throw new RuntimeException("cannot find difference");
    }
}
