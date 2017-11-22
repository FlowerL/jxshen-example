package com.jxshen.example.leetcode;

public class ConvertSortedArrayToBinarySearchTree108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }

        int begin = 0;
        int end = nums.length - 1;
        return sort(nums, begin, end);
    }

    private static TreeNode sort(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return new TreeNode(nums[begin]);
        }

        int mid = (begin + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sort(nums, begin, mid - 1);
        node.right = sort(nums, mid + 1, end);
        return node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
