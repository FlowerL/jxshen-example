package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch401 {

    public static void main(String[] args) {
        BinaryWatch401 binaryWatch401 = new BinaryWatch401();
        System.out.println(binaryWatch401.readBinaryWatch(0));
    }

    public List<String> readBinaryWatch(int num) {
        if (num < 0) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    res.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return res;
    }
}
