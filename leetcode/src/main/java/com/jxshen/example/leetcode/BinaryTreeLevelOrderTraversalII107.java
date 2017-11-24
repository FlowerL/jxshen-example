package com.jxshen.example.leetcode;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) {q.offer(node.left);}
                if (node.right != null) {q.offer(node.right);}
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
