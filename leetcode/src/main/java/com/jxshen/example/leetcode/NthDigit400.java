package com.jxshen.example.leetcode;

public class NthDigit400 {

    public static void main(String[] args) {
        System.out.println(new NthDigit400().findNthDigit(3));
    }

    public int findNthDigit(int n) {
        long digit = 1;
        long base = 9;
        long ibegin = 1;
        while (n < digit * base) {
            n -= base * digit;
            digit++;
            ibegin += base;
            base *= 10;
        }

        return ("" + ibegin + (n - 1) / digit).charAt((int)((n - 1) % digit)) - '0';
    }
}
