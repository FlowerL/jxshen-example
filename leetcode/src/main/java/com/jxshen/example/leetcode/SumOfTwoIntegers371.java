package com.jxshen.example.leetcode;

public class SumOfTwoIntegers371 {

    public int getSum(int a, int b) {
        if (b == 0) {
            return a;
        }

        int sum = a ^ b;
        int carray = (a & b) << 1;
        return getSum(sum, carray);
    }
}
