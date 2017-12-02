package com.jxshen.example.leetcode;

public class LongestUnivaluePath687 {

    private int res = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        arrowLength(root);
        return res;
    }

    private int arrowLength(TreeNode root) {
        if (root == null)
            return 0;

        int l = arrowLength(root.left);
        int r = arrowLength(root.right);
        int arrowL = 0;
        int arrowR = 0;
        if (root.left != null && root.left.val == root.val) {
            arrowL = l + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            arrowR = r + 1;
        }
        res = Math.max(res, arrowL + arrowR);
        return Math.max(arrowL, arrowR);
    }
}
