package com.jxshen.example.leetcode;

public class ConstructTheRectangle492 {

    public int[] constructRectangle(int area) {
        if (area < 1) {
            return new int[2];
        }

        int H = (int)Math.sqrt(area);
        while (area % H != 0) {
            H--;
        }
        return new int[]{area/H, H};
    }
}
