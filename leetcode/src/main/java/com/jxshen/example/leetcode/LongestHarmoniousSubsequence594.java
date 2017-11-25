package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence594 {

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,5,2,3,7};
        System.out.println(findLHS(nums));
    }

    public static int findLHS(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Integer count = 0;
        Integer Lcount = 0;
        Integer Rcount = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            count = map.get(num);

            Lcount = map.get(num - 1);
            if (Lcount != null) {
                res = Lcount + count > res ? Lcount + count : res;
            }

            Rcount = map.get(num + 1);
            if (Rcount != null) {
                res = Rcount + count > res ? Rcount + count : res;
            }
        }

        return res;
    }
}
