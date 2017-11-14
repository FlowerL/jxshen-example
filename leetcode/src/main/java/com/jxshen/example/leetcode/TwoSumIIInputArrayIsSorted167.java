package com.jxshen.example.leetcode;

public class TwoSumIIInputArrayIsSorted167 {

    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int sum = 0;
        for (int i = 0, j = numbers.length - 1; i < j;) {
            sum = numbers[i] + numbers[j];
            if (sum == target) {
                res[0] = i + 1;
                res[1] = j + 1;
                break;
            }
            if (sum > target) {
                j--;
            }
            if (sum < target) {
                i++;
            }
        }
        return res;
    }
}
