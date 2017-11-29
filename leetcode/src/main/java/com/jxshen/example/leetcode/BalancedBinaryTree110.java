package com.jxshen.example.leetcode;

public class BalancedBinaryTree110 {

    public boolean isBalanced(TreeNode root) {
        return calHeight(root) != -1;
    }

    public int calHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lheight = calHeight(node.left);
        if (lheight == -1) {
            return -1;
        }
        int rheight = calHeight(node.right);
        if (rheight == -1) {
            return -1;
        }
        if (Math.abs(lheight - rheight) > 1 ) {
            return -1;
        }
        return Math.max(lheight, rheight) + 1;
    }
}
