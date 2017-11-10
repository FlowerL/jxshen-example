package com.jxshen.example.leetcode;

import java.util.TreeSet;

public class MinimumAbsoluteDifferenceInBST530 {

    private int res = Integer.MAX_VALUE;
    private Integer preVal = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return res;
        }
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (preVal != null) {
            res = Math.min(res, root.val - preVal);
        }
        preVal = root.val;
        inorder(root.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private TreeSet<Integer> treeSet = new TreeSet<>();

    public int getMinimumDifferenceTreeSet(TreeNode root) {
        if (root == null) {
            return res;
        }
        Integer floor = treeSet.floor(root.val);
        if (floor != null) {
            res = Math.min(res, root.val - floor);
        }
        Integer ceiling = treeSet.ceiling(root.val);
        if (ceiling != null) {
            res = Math.min(res, ceiling - root.val);
        }

        treeSet.add(root.val);
        getMinimumDifference(root.left);
        getMinimumDifference(root.right);

        return res;
    }
}
