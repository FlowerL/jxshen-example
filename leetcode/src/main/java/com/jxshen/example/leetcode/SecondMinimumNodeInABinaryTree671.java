package com.jxshen.example.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SecondMinimumNodeInABinaryTree671 {

    public static void main(String[] args) {
        TreeNode root = generateTree(new Integer[]{5,8,5});
        System.out.println(findSecondMinimumValue(root));
    }

    public static int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) {
            return -1;
        }
        int min1 = root.val;
        int min2 = min1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (min1 == min2 && node.val <= min1) {
                    min1 = node.val;
                    if (node.left != null) {q.offer(node.left);}
                    if (node.right != null) {q.offer(node.right);}
                }
                else if (min1 == min2 && node.val > min1) {
                    min2 = node.val;
                    if (node.left != null && node.left.val == node.val) {q.offer(node.left);}
                    if (node.right != null && node.right.val == node.val) {q.offer(node.right);}
                }
                else if (min1 < min2 && node.val <= min1) {
                    min2 = min1 == node.val ? min2 : min1;
                    min1 = node.val;
                    if (node.left != null) {q.offer(node.left);}
                    if (node.right != null) {q.offer(node.right);}
                }
                else if (min1 < min2 && node.val <= min2) {
                    min2 = node.val;
                }
            }
        }
        return min1 == min2 ? -1 : min2;
    }

    private static TreeNode generateTree(Integer[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean isLeftSet = false;
        for (int i = 1; i < nums.length; i++) {
            TreeNode father = q.peek();

            if (nums[i] == null) {
                if (!isLeftSet) {
                    isLeftSet = true;
                }
                else {
                    isLeftSet = false;
                    q.poll();
                }
                continue;
            }
            TreeNode son = new TreeNode(nums[i]);
            q.offer(son);

            if (!isLeftSet) {
                father.left = son;
                isLeftSet = true;
            }
            else {
                father.right = son;
                isLeftSet = false;
                q.poll();
            }
        }
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
