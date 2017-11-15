package com.jxshen.example.leetcode;

import java.util.*;

public class MinimumIndexSumOfTwoLists599 {

    public static void main(String[] args) {
        String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = {"Piatti","The Grill at Torrey Pines","Tapioca Express","Shogun"};
        MinimumIndexSumOfTwoLists599 minimumIndexSumOfTwoLists599 = new MinimumIndexSumOfTwoLists599();
        String[] res = minimumIndexSumOfTwoLists599.findRestaurant(list1, list2);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list2 == null || list1.length < 1 || list2.length < 1) {
            return new String[0];
        }

        Map<String, Integer> list1Str2IndexMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            list1Str2IndexMap.put(list1[i], i);
        }
        TreeMap<Integer, Set<String>> indexSum2StrMap = new TreeMap<>();
        for (int i = 0; i < list2.length; i++) {
            Integer j = list1Str2IndexMap.get(list2[i]);
            if (j != null) {
                Integer indexSum = i + j;
                Set<String> set = indexSum2StrMap.get(indexSum);
                if (set == null) {
                    set = new HashSet<>();
                    indexSum2StrMap.put(indexSum, set);
                }
                set.add(list2[i]);
            }
        }
        return indexSum2StrMap.firstEntry().getValue().toArray(new String[0]);
    }
}
