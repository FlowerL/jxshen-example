package com.jxshen.example.leetcode;

// 采用中位数作为基准
public class RelativeRanks506 {

    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new String[0];
        }

        quickSort(nums, 0, nums.length - 1);
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            switch (i) {
                case 0:
                    res[i] = "Gold Medal";
                    break;
                case 1:
                    res[i] = "Silver Medal";
                    break;
                case 2:
                    res[i] = "Bronze Medal";
                    break;
                default:
                    res[i] = (i + 1) + "";
                    break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10,3,8,9,4};
        quickSort(nums, 0, nums.length -1);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void quickSort(int[] nums, int begin, int end) {
        if (begin >= end) {
            return;
        }

        swapMedianToLow(nums, begin, end);
        int i = begin;
        int j = end;
        int pivot = nums[begin];
        while (i < j) {
            while (pivot <= nums[j] && i < j) {
                --j;
            }
            while (pivot >= nums[i] && i < j) {
                ++i;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, begin, i);
        quickSort(nums, begin, i -1);
        quickSort(nums, i + 1, end);
    }

    private static void swapMedianToLow(int[] nums, int begin, int end) {
        int mid = begin + (end - begin) /2 ;
        if (nums[mid] > nums[end]) {
            swap(nums, mid, end);
        }
        if (nums[begin] > nums[end]) {
            swap(nums, begin, end);
        }
        if (nums[begin] < nums[mid]) {
            swap(nums, begin, end);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
