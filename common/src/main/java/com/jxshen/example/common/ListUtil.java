package com.jxshen.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {

    private static final String REGEX = ",|，|;|；|\n|\r\n";
    
    /**
     * 按默认分隔符对字符串进行分隔，并裁剪分割后每个字符串的前后空格<br>
     * 默认分隔符：英文逗号、英文分号、中文逗号、中文分号，各种换行制表符
     */
    public static List<String> splitAndTrim(String src) {
        return splitTrim(src, REGEX);
    }
    
    /**
     * 按正则表达式对字符串进行分隔，并裁剪分割后每个字符串的前后空格<br>
     */
    public static List<String> splitTrim(String src, String regex) {
        if (regex == null || regex.isEmpty() || src == null || src.isEmpty()) {
            return null;
        }
        
        List<String> splitList = Arrays.asList(src.split(regex));
        List<String> dst = null;
        if (splitList != null && !splitList.isEmpty()) {
            dst = new ArrayList<String>();
            for (String str : splitList) {
                String trimedStr = str.trim();
                if (!isBlank(trimedStr)) {
                    dst.add(trimedStr);
                }
                
            }
        }
        
        return dst;
    }
    
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
