package com.jxshen.example.leetcode;

public class MaxAreaOfIsland695 {

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        if (grid == null || grid.length <= 0) {
            return res;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = calArea(grid, i, j);
                    res = area > res ? area : res;
                }
            }
        }
        return res;
    }

    private static int calArea(int[][] grid, int i, int j) {
        int area = 0;
        int height = grid.length;
        int weight = grid[0].length;

        if (grid[i][j] == 1) {
            area++;
            grid[i][j] = 0;

            if (i > 0) {
                area += calArea(grid, i - 1, j);
            }
            if (j > 0) {
                area += calArea(grid, i, j - 1);
            }
            if (i < height - 1) {
                area += calArea(grid, i + 1, j);
            }
            if (j < weight - 1) {
                area += calArea(grid, i, j + 1);
            }
        }

        return area;
    }
}
