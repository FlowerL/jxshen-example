package com.jxshen.example.leetcode;

public class BinaryTreeTilt563 {

    private int sumOfTilt = 0;

    public int findTilt(TreeNode root) {
        traverse(root);
        return sumOfTilt;
    }

    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = traverse(root.left);
        int rightSum = traverse(root.right);
        sumOfTilt += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
