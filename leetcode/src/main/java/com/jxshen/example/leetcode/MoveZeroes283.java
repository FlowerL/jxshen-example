package com.jxshen.example.leetcode;

public class MoveZeroes283 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }

        int curIndex = 0;
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }
            if (nums[i] != 0) {
                if (curIndex < i) {
                    nums[curIndex] = nums[i];
                }
                curIndex++;
            }
        }
        for (int i = 0; i < zeroCount; i++) {
            nums[nums.length - 1 -i] = 0;
        }
    }
}
