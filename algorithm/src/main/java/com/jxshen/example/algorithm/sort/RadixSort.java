package com.jxshen.example.algorithm.sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        
        // Hash桶
        Map<Integer, List<String>> bucket = new HashMap<Integer, List<String>>();
        int maxLength = 1; // 所有排序字符串中最大的长度
        int curRadix = 0; // 当前基数
        do {
            for (String str : source) {
                if (str.length() > maxLength) {
                    maxLength = str.length();
                }
            }
        } while (curRadix < maxLength);
        
        return null;
    }
    
    public static void main(String[] args) {
    }
}
