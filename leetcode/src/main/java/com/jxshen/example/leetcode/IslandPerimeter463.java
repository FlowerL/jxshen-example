package com.jxshen.example.leetcode;

public class IslandPerimeter463 {

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        int height = grid.length;
        int weight = grid[0].length;
        int res = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i > 0 && grid[i-1][j] == 1) {
                        res -= 2;
                    }
                    if (j > 0 && grid[i][j-1] == 1){
                        res -= 2;
                    }
                }
            }
        }
        return res;
    }
}
