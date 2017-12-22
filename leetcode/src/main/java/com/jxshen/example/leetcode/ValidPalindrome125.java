package com.jxshen.example.leetcode;

public class ValidPalindrome125 {

    public boolean isPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int gap = 'a' - 'A';
            if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                sb.append(ch);
            }
            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char)(ch + gap));
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        String s = "0P";
        new ValidPalindrome125().isPalindrome(s);
    }
}
