package com.jxshen.example.leetcode;

import java.util.TreeSet;

public class Heaters475 {

    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int heater : heaters) {
            set.add(heater);
        }

        int res = Integer.MIN_VALUE;
        for (int house : houses) {
            Integer left = set.floor(house);
            Integer right = set.ceiling(house);
            left = left == null ? Integer.MAX_VALUE : house - left;
            right = right == null ? Integer.MAX_VALUE : right - house;
            res = res < Math.min(left, right) ? Math.min(left, right) : res;
        }
        return res;
    }
}
