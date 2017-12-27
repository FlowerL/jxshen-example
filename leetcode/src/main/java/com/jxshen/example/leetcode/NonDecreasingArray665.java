package com.jxshen.example.leetcode;

public class NonDecreasingArray665 {

    public static void main(String[] args) {
        new NonDecreasingArray665().checkPossibility(new int[]{3,4,2,3});
    }

    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }

        boolean modify = false;
        for (int i = 1, pre = nums[0]; i < nums.length; i++) {
            if (pre > nums[i]) {
                if (modify) {
                    return false;
                }
                if (i - 2 < 0 || nums[i-2] <= nums[i]) {
                    pre = nums[i];
                }
                modify = true;
            }
            else {
                pre = nums[i];
            }
        }
        return true;
    }
}
