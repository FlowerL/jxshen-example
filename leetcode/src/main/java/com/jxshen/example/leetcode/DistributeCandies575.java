package com.jxshen.example.leetcode;

import java.util.HashSet;
import java.util.Set;

public class DistributeCandies575 {

    public int distributeCandies(int[] candies) {
        int length = candies.length;
        int halfLength = length / 2;
        Set<Integer> candySet = new HashSet<>();
        for (Integer candy : candies) {
            candySet.add(candy);
        }
        return candySet.size() > halfLength ? halfLength : candySet.size();
    }
}
