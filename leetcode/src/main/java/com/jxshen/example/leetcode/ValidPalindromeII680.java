package com.jxshen.example.leetcode;

public class ValidPalindromeII680 {

    public static void main(String[] args) {
        System.out.println(new ValidPalindromeII680().validPalindrome("atbbga"));
    }

    public boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }

        int r = -1;
        int l = s.length();
        while (++r < --l) {
            if (s.charAt(r) != s.charAt(l)) {
                return isPalindrome(s, r-1, l) || isPalindrome(s, r, l+1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int r, int l) {
        while (++r < --l) {
            if (s.charAt(r) != s.charAt(l)) {
                return false;
            }
        }
        return true;
    }
}
