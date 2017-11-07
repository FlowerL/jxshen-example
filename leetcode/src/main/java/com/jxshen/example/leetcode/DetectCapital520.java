package com.jxshen.example.leetcode;

public class DetectCapital520 {

    public boolean detectCapitalUse(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        char[] chars = word.toCharArray();
        boolean isStartUpperCase = Character.isUpperCase(chars[0]);
        if (chars.length == 1) {
            return true;
        }
        if (chars.length == 2) {
            if (isStartUpperCase) {
                return true;
            }
            if (Character.isLowerCase(chars[0]) && Character.isLowerCase(chars[1])) {
                return true;
            }
            return false;
        }
        boolean isSecondUpperCase = Character.isUpperCase(chars[1]);
        if (!isStartUpperCase && isSecondUpperCase) {
            return false;
        }
        boolean shouldAllUpperCase = isStartUpperCase && isSecondUpperCase;
        for (int i = 2; i < chars.length; i++) {
            if (shouldAllUpperCase && Character.isLowerCase(chars[i])) {
                return false;
            }
            if (!shouldAllUpperCase && Character.isUpperCase(chars[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean detectCapitalUse2(String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }
}
