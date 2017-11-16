package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs447 {

    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length < 1) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int d = calDistance(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                res += entry.getValue() * (entry.getValue() - 1);
            }
            map.clear();
        }
        return res;
    }

    private static int calDistance(int[] x, int[] y) {
        int dx = x[0] - y[0];
        int dy = x[1] - y[1];
        return dx*dx + dy*dy;
    }
}
