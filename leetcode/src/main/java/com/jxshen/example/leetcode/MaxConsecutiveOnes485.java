package com.jxshen.example.leetcode;

public class MaxConsecutiveOnes485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        if (nums == null) {
            return res;
        }

        int current = 0;
        for (int num : nums) {
            if (num == 1) {
                current++;
            }
            if (num == 0) {
                res = current > res ? current : res;
                current = 0;
            }
        }
        return res = current > res ? current : res;
    }
}
