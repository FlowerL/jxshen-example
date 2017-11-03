package com.jxshen.example.leetcode;

public class TrimABinarySearchTree669 {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null || L > R) {
            return null;
        }

        if (root.val < L) {
            root.left = null;
            root = trimBST(root.right, L, R);
            return root;
        }
        if (root.val > R) {
            root.right = null;
            root = trimBST(root.left, L, R);
            return root;
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L ,R);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
