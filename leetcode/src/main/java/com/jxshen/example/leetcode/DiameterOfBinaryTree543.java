package com.jxshen.example.leetcode;

public class DiameterOfBinaryTree543 {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lDepth = depth(root.left);
        int rDepth = depth(root.right);
        int lDiam = diameterOfBinaryTree(root.left);
        int rDiam = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(lDiam, rDiam), lDepth + rDepth);
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(depth(root.left), depth(root.right));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
