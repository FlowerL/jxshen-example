package com.jxshen.example.leetcode;

import java.util.Stack;

public class ConstructStringFromBinaryTree606 {

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        str.append(t.val);
        String leftStr = tree2str(t.left);
        String rightStr = tree2str(t.right);
        if ("".equals(leftStr) && "".equals(rightStr)) {
            return str.toString();
        }
        str.append(String.format("(%s)", leftStr));
        if (!"".equals(rightStr)) {
            str.append(String.format("(%s)", rightStr));
        }
        return str.toString();
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
