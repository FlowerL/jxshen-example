package com.jxshen.example.algorithm.sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基数排序，对输入的String统一按ASCII码从小到大排序
 * 
 * @author jxshen
 *
 */
public class RadixSort {

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
    }
}
