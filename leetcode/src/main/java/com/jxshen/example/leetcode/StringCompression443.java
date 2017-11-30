package com.jxshen.example.leetcode;

public class StringCompression443 {

    public int compress(char[] chars) {
        if (chars == null || chars.length < 1) {
            return 0;
        }

        int anchor = 0;
        int write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read == chars.length - 1 || chars[read+1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c : ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }
}
