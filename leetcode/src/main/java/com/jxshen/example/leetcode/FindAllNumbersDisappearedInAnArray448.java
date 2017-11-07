package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 2) {
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int mapIndex = Math.abs(nums[i]) - 1;
            nums[mapIndex] = - Math.abs(nums[mapIndex]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i+1);
            }
        }
        return res;
    }
}
