package com.jxshen.example.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class RelativeRanks506 {

    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new String[0];
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int i = 0;
        String[] res = new String[nums.length];
        for (Map.Entry<Integer, Integer> entry : map.descendingMap().entrySet()) {
            i++;
            switch (i) {
                case 1:
                    res[entry.getValue()] = "Gold Medal";
                    break;
                case 2:
                    res[entry.getValue()] = "Silver Medal";
                    break;
                case 3:
                    res[entry.getValue()] = "Bronze Medal";
                    break;
                default:
                    res[entry.getValue()] = i + "";
            }
        }
        return res;
    }



}
