package com.jxshen.example.leetcode;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length < 1|| nums2.length < 1) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        for (Integer num : nums1) {
            set1.add(num);
        }
        Set<Integer> resSet = new HashSet<>();
        for (Integer num : nums2) {
            if (set1.contains(num)) {
                resSet.add(num);
            }
        }
        int[] res = new int[resSet.size()];
        int i = 0;
        for (int num : resSet) {
            res[i++] = num;
        }
        return res;
    }
}
