package com.jxshen.example.leetcode;

import java.util.*;

public class IntersectionOfTwoArraysII350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
            return new int[0];
        }

        int[] res = new int[Math.max(nums1.length, nums2.length)];
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            Integer count = map.get(num);
            if (count == null) {
                count = 0;
            }
            map.put(num, ++count);
        }

        for (int num : nums2) {
            Integer count = map.get(num);
            if (count != null && count > 0) {
                res[total++] = num;
                map.put(num, --count);
            }
        }

        return Arrays.copyOf(res, total);
    }
}
