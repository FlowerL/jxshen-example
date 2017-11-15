package com.jxshen.example.leetcode;

public class ImageSmoother661 {

    public int[][] imageSmoother(int[][] M) {
        if (M == null || M.length < 1) {
            return new int[0][];
        }

        int width = M[0].length;
        int height = M.length;
        int[][] res = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int sum = M[i][j];
                int count = 1;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum += M[i-1][j-1];
                    count++;
                }
                if (i - 1 >= 0) {
                    sum += M[i-1][j];
                    count++;
                }
                if (i - 1 >= 0 && j + 1 < width) {
                    sum += M[i-1][j+1];
                    count++;
                }
                if (j - 1 >= 0) {
                    sum += M[i][j-1];
                    count++;
                }
                if (j + 1 < width) {
                    sum += M[i][j+1];
                    count++;
                }
                if (i + 1 < height && j - 1 >= 0) {
                    sum += M[i+1][j-1];
                    count++;
                }
                if (i + 1 < height) {
                    sum += M[i+1][j];
                    count++;
                }
                if (i + 1 < height && j + 1 < width) {
                    sum += M[i+1][j+1];
                    count++;
                }
                res[i][j] = sum / count;
            }
        }
        return res;
    }
}
