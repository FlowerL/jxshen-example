package com.jxshen.example.leetcode;

public class LongestContinuousIncreasingSubsequence674 {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int res = 0;
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > pre) {
                count++;
            }
            else {
                res = res < count ? count : res;
                count = 1;
            }
            pre = nums[i];
        }
        return res < count ? count : res;
    }
}
