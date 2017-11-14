package com.jxshen.example.algorithm.sort;

public class QuickSort {

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
