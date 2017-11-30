package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindModeInBinarySearchTree501 {

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        calMode(root);
        tmpMode = 0;
        pre = null;
        doFindMode(root);
        return convertRes(list);
    }

    int mode = 0;
    int tmpMode = 0;
    Integer pre = null;

    private void calMode(TreeNode root) {
        if (root == null) {
            return;
        }

        calMode(root.left);

        if (pre == null || root.val != pre) {
            pre = root.val;
            tmpMode = 1;

        }
        else {
            tmpMode++;
        }
        mode = tmpMode > mode ? tmpMode : mode;

        calMode(root.right);
    }

    List<Integer> list = new ArrayList<Integer>();

    private void doFindMode(TreeNode root) {
        if (root == null) {
            return;
        }

        doFindMode(root.left);

        if (pre == null || root.val != pre) {
            pre = root.val;
            tmpMode = 1;
        }
        else if (root.val == pre) {
            tmpMode++;
        }
        if (tmpMode == mode) {
            list.add(root.val);
        }

        doFindMode(root.right);
    }

    private int[] convertRes(List<Integer> list) {
        int[] res = new int[list.size()];
        int i = 0;
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            res[i++] = iter.next();
        }
        return res;
    }
}
