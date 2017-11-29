package com.jxshen.example.leetcode;

public class ValidPerfectSquare367 {

    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) {
            return true;
        }

        int half = num / 2;
        do {
            if (num == half * half) {
                return true;
            }
            half--;
        } while (half > 0);
        return false;
    }
}
