package com.jxshen.example.leetcode;

public class CanPlaceFlowers605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length < 1 || n > flowerbed.length) {
            return false;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if ((i - 1 < 0 || flowerbed[i-1] == 0) && (i + 1 >= flowerbed.length || flowerbed[i+1] ==0)) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n <= 0;
    }
}
