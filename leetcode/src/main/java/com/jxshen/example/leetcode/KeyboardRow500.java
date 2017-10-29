package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardRow500 {

    public static final List<Character> LINE_1;
    public static final List<Character> LINE_2;
    public static final List<Character> LINE_3;

    static {
        LINE_1 = str2CharList("qwertyuiopQWERTYUIOP");
        LINE_2 = str2CharList("asdfghjklASDFGHJKL");
        LINE_3 = str2CharList("zxcvbnmZXCVBNM");
    }

    private static List<Character> str2CharList(String str) {
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (Character cha : chars) {
            list.add(cha);
        }
        return list;
    }

    public String[] findWords(String[] words) {
        if (words == null || words.length <= 0) {
            return new String[]{};
        }
        return Arrays.stream(words).filter(word -> {
            if (word == null || word.isEmpty()) {
                return false;
            }
            return canTypeInOneRow(word);
        }).toArray(String[]::new);
    }

    private static boolean canTypeInOneRow(String word) {
        List<Character> charList = str2CharList(word);
        int firstCharLine = findCharLine(charList.get(0));
        for (Character cha : charList) {
            if (firstCharLine != findCharLine(cha)) {
                return false;
            }
        }
        return true;
    }

    private static int findCharLine(Character cha) {
        if (LINE_1.contains(cha)) {
            return 1;
        }
        if (LINE_2.contains(cha)) {
            return 2;
        }
        return 3;
    }

}
