package com.jxshen.example.leetcode;

public class FindPivotIndex724 {

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int sumL = 0;
        int sumR = sum;
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 >= 0) {
                sumL += nums[i-1];
            }
            sumR -= nums[i];
            if (sumL == sumR) {
                return i;
            }
        }
        return -1;
    }
}
