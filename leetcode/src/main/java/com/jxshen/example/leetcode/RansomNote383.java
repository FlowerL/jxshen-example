package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RansomNote383 {

    public boolean canConstruct(String ransomNote, String magazine) {

        boolean isRansomNotEmpty = ransomNote == null || ransomNote.isEmpty();
        boolean isMagazineEmtpy = magazine == null || magazine.isEmpty();
        if (isRansomNotEmpty && isMagazineEmtpy) {
            return true;
        }
        if (isRansomNotEmpty) {
            return true;
        }
        if (isMagazineEmtpy) {
            return false;
        }
        char[] mChars = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : mChars) {
            Integer count = map.get(c);
            if (count == null) {
                count = 0;
            }
            map.put(c, ++count);
        }
        char[] rChars = ransomNote.toCharArray();
        for (char c : rChars) {
            Integer count = map.get(c);
            if (count == null || --count < 0) {
                return false;
            }
            map.put(c, count);
        }
        return true;
    }
}
