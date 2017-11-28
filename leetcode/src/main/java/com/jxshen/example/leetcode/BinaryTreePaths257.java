package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreePaths257 {

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<String> res = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        doBinaryTreePath(root, prefix, res);
        return res;
    }

    private static void doBinaryTreePath(TreeNode root, StringBuilder prefix, List<String> res) {
        int curIndex = prefix.length();
        prefix.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(prefix.toString());
        }
        prefix.append("->");
        int nextIndex = prefix.length();
        if (root.left != null) {
            doBinaryTreePath(root.left, prefix, res);
            prefix.delete(nextIndex, prefix.length());
        }
        if (root.right != null) {
            doBinaryTreePath(root.right, prefix, res);
        }
        prefix.delete(curIndex, prefix.length());
    }
}
