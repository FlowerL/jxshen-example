package com.jxshen.example.algorithm.sort;

import java.util.*;

/**
 * 基数排序，对输入的String统一按ASCII码从小到大排序
 * 
 * @author jxshen
 *
 */
public class RadixSort {

//    public static int[] sortInteger(int[] nums) {
//        if (nums == null || nums.length < 1) {
//            return new int[0];
//        }
//
//        List<Integer> listNums = intArray2List(nums, LinkedList.class);
//        List<int[]> listSpitNums = splitInt(listNums);
//
//        // 求最大位数
//        int maxRadix = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int radix = calRadix(nums[i]);
//            maxRadix = maxRadix < radix ? radix : maxRadix;
//        }
//
//        Map<Integer, List<Integer>> buckets = new TreeMap<>();
//    }

//    public static List<int[]> splitInt(List<Integer> list, Class<? extends List> clzz) {
//
//    }

    public static List<Integer> intArray2List(int[] nums, Class<? extends List> clzz) {
        List<Integer> res;
        try {
            res = clzz.newInstance();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }

        for (int num : nums) {
            res.add(num);
        }
        return res;
    }

    public static int calRadix(int num) {
        int res = 1;
        while ((num /= 10) != 0) {
            res++;
        }
        return res;
    }


    public static List<List<Integer>> splitInteger(List<Integer> src) {
        List<List<Integer>> res = new LinkedList<>();
        Iterator<Integer> iter = src.iterator();
        while (iter.hasNext()) {
            Integer srcInt = iter.next();
            List<Integer> tmp = new LinkedList<>();
            while (srcInt != 0) {
                tmp.add(srcInt % 10);
                srcInt /= 10;
            }
            reverse(tmp);
            res.add(tmp);
        }
        return res;
    }

    public static void reverse(List<Integer> src) {
        int i = 0, j = src.size() - 1;
        while (i < j) {
            swap(src, i++, j--);
        }
    }

    public static void swap(List<Integer> list, int a, int b) {
        int tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);

    }

    public static List<String> itoa(List<Integer> list) {
        List<String> res = new ArrayList<>();
        for (Integer i : list) {
            res.add(itoa(i));
        }
        return res;
    }

    public static String itoa(Integer n) {
        StringBuilder sb = new StringBuilder();
        int radix;
        while ((radix = n % 10) != 0) {
            sb.append(radix);
            n /= 10;
        }
        return sb.reverse().toString();
    }

    public static List<String> sort(List<String> source) {
        if (source == null || source.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        
        List<String> result = new LinkedList<String>(source);
        // 最大位数
        int maxLength = 0;
        for (String str : source) {
            if (str.length() > maxLength) {
                maxLength = str.length();
            }
        }
        
        Map<Character, List<String>> bucket = new TreeMap<Character, List<String>>();
        // 基数排序
        for (int i = 1; i <= maxLength; i++) {
            Iterator<String> iter = result.iterator();
            while (iter.hasNext()) {
                String str = iter.next();
                // 如果字符串有本次循环基数位的数值，则参与排序，否则留在result里面
                if (str.length() < (i)) {
                    continue;
                }
                iter.remove();
                char radixChar = str.charAt(str.length() - i);
                List<String> radixList = bucket.get(radixChar);
                if (radixList == null) {
                    radixList = new LinkedList<String>();
                    bucket.put(radixChar, radixList);
                }
                radixList.add(str);
            }
            // 将基数桶里面的数据按char由小到大添加到result后面
            Iterator<Map.Entry<Character, List<String>>> entryIter = bucket.entrySet().iterator();
            while (entryIter.hasNext()) {
                Map.Entry<Character, List<String>> entry = entryIter.next();
                entryIter.remove();
                result.addAll(entry.getValue());
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
//        Map<Character, Integer> map = new TreeMap<Character, Integer>();
//        map.put('h', 1);
//        map.put('H', 1);
//        map.put('3', 1);
//        map.put('4', 1);
//        Iterator<Map.Entry<Character, Integer>> entryIter = map.entrySet().iterator();
//        while (entryIter.hasNext()) {
//            Map.Entry<Character, Integer> entry = entryIter.next();
//            entryIter.remove();
//            System.out.println(entry);
//        }
        List<String> source = new LinkedList<String>();
        source.add("1af23");
        source.add("3");
        source.add("h21");
        source.add("421");
        source.add("H21");
        source.add("312");
        System.out.println(sort(source));

        Integer n = 1234;
        System.out.println(itoa(n));
        System.out.println("123123".equals(null));
    }
}
