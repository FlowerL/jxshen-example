package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstUniqueCharacterInAString387 {

    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }

        Map<Character, Integer> map = new HashMap<>();
        char[] sChars = s.toCharArray();
        for (char c : sChars) {
            Integer feq = map.get(c);
            if (feq == null) {
                feq = 0;
            }
            map.put(c, ++feq);
        }
        int res = -1;
        for (int i = 0; i < sChars.length; i++) {
            if (map.get(sChars[i]) == 1) {
                res =  i;
                break;
            }
        }
        return res;
    }
}
