package com.jxshen.example.leetcode;

public class RangeAdditionII598 {

    public int maxCount(int m, int n, int[][] ops) {
        if (m <= 0 || n <=0) {
            return 0;
        }
        if (ops == null || ops.length < 1 || ops[0].length < 1) {
            return m * n;
        }

        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        for (int[] op : ops) {
            minA = op[0] < minA ? op[0] : minA;
            minB = op[1] < minB ? op[1] : minB;
        }
        return minA * minB;
    }
}
