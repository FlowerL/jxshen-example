package com.jxshen.example.leetcode;

public class BinaryNumberWithAlternatingBits693 {
    public boolean hasAlternatingBits(int n) {
        int pre = n & 1;
        do {
            n >>= 1;
            int next = n & 1;
            if (pre == next) {
                return false;
            }
            pre = next;
        } while (n > 0);
        return true;
    }
}
