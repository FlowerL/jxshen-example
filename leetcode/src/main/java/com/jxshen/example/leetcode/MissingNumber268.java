package com.jxshen.example.leetcode;

public class MissingNumber268 {

    public int missingNumber(int[] nums) {
        int length = 0;
        int sumOfNums = 0;
        for (int num : nums) {
            length++;
            sumOfNums += num;
        }

        int sumOfN = 0;
        for (int i = 0; i <= length; i++) {
            sumOfN += i;
        }

        return sumOfN - sumOfNums;
    }
}
