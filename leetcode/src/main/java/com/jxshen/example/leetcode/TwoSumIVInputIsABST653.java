package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIVInputIsABST653 {

    private static Map<Integer, Integer> map = new HashMap<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        inorder(root);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer other = k - entry.getKey();
            if (other == entry.getKey() && entry.getValue() > 1) {
                return true;
            }
            if (other == entry.getKey() && entry.getValue() <= 1) {
                continue;
            }
            if (map.get(other) != null) {
                return true;
            }
        }
        return false;
    }

    private static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        Integer count = map.get(root.val);
        if (count == null) {
            map.put(root.val, 1);
        }
        else {
            map.put(root.val, ++count);
        }
        inorder(root.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
