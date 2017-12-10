package com.jxshen.example.leetcode;

import java.util.Arrays;

public class ShortestUnsortedContinuousSubarray581 {

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int res = nums.length;
        int i = 0, j = nums.length - 1;

        int[] minHeap = Arrays.copyOf(nums, nums.length);
        buildMinHeap(minHeap);
        int lastIndex = minHeap.length - 1;
        while (i <= j) {
            if (nums[i] == minHeap[0]) {
                i++;
                res--;
                swap(minHeap, 0, lastIndex);
                lastIndex--;
                adjustMinHeap(minHeap, 0, lastIndex);
            }
            else {
                break;
            }
        }

        int[] maxHeap = Arrays.copyOf(nums, nums.length);
        buildMaxHeap(maxHeap);
        lastIndex = maxHeap.length - 1;
        while (j >= i) {
            if (nums[j] == maxHeap[0]) {
                j--;
                res--;
                swap(maxHeap, 0, lastIndex);
                lastIndex--;
                adjustMaxHeap(maxHeap, 0, lastIndex);
            }
            else {
                break;
            }
        }
        return res;

    }

    private void buildMinHeap(int[] nums) {
        for (int i = nums.length/2 - 1; i >=0; i--) {
            adjustMinHeap(nums, i, nums.length - 1);
        }
    }

    private void adjustMinHeap(int[] nums, int begin, int end) {
        int left = 2 * begin + 1;
        int right = 2 * begin + 2;
        boolean hasLeft = left <= end;
        boolean hasRight = right <= end;

        if (hasLeft && hasRight) {
            if (nums[left] < nums[begin] && nums[left] <= nums[right]) {
                swap(nums, left, begin);
                adjustMinHeap(nums, left, end);
            }
            else if (nums[right] < nums[begin] && nums[right] <= nums[left]) {
                swap(nums, right, begin);
                adjustMinHeap(nums, right, end);
            }
        }
        else if (hasLeft && nums[left] < nums[begin]) {
            swap(nums, left, begin);
        }
    }

    private void buildMaxHeap(int[] nums) {
        for (int i = nums.length/2 - 1; i >= 0; i--) {
            adjustMaxHeap(nums, i, nums.length - 1);
        }
    }

    private void adjustMaxHeap(int[] nums, int begin, int end) {
        int left = 2 * begin + 1;
        int right = 2 * begin + 2;
        boolean hasLeft = left <= end;
        boolean hasRight = right <= end;

        if (hasLeft && hasRight) {
            if (nums[left] > nums[begin] && nums[left] >= nums[right]) {
                swap(nums, left, begin);
                adjustMaxHeap(nums, left, end);
            }
            else if (nums[right] > nums[begin] && nums[right] >= nums[left]) {
                swap(nums, right, begin);
                adjustMaxHeap(nums, right, end);
            }
        }
        else if (hasLeft && nums[left] > nums[begin]) {
            swap(nums, left, begin);
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
