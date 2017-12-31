package com.jxshen.example.leetcode;

public class MaximumBinaryTree654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }

        return buildMaxBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildMaxBinaryTree(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = begin;
        for (int i = begin; i <= end; i++ ) {
            if (max < nums[i]) {
                maxIndex = i;
                max = nums[i];
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = buildMaxBinaryTree(nums, begin, maxIndex - 1);
        root.right = buildMaxBinaryTree(nums, maxIndex + 1, end);
        return root;
    }
}
