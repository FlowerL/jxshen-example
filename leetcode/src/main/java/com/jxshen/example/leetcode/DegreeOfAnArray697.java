package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray697 {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        Map<Integer, DegreeInfo> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            DegreeInfo degreeInfo = map.get(nums[i]);
            if (degreeInfo == null) {
                degreeInfo = new DegreeInfo();
                map.put(nums[i], degreeInfo);
            }
            degreeInfo.degree++;
            if (degreeInfo.fisrt < 0) {
                degreeInfo.fisrt = i;
            }
            degreeInfo.last = i;
        }
        int degree = 0;
        int smallest = Integer.MAX_VALUE;
        for (Map.Entry<Integer, DegreeInfo> entry : map.entrySet()) {
            DegreeInfo degreeInfo = entry.getValue();
            int length = degreeInfo.last - degreeInfo.fisrt + 1;
            if (degreeInfo.degree == degree && length < smallest) {
                smallest = length;
            }
            if (degreeInfo.degree > degree) {
                degree = degreeInfo.degree;
                smallest = length;
            }
        }
        return smallest;
    }

    private static final class DegreeInfo {
        int degree = 0;
        int fisrt = -1;
        int last = -1;
    }
}
