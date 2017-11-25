package com.jxshen.example.leetcode;

public class SubtreeOfAnotherTree572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == t;
        }
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSame(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return s == t;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
