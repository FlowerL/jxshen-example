package com.jxshen.example.leetcode;

public class ArrangingCoins441 {

    public int arrangeCoins(int n) {
        int res = 0;
        int iter = 1;
        while (true) {
            n -= iter;
            iter++;
            if (n >= 0) {
                res++;
            }
            else {
                break;
            }
        }
        return res;
    }
}
