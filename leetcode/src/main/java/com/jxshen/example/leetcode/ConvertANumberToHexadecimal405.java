package com.jxshen.example.leetcode;

public class ConvertANumberToHexadecimal405 {

    public String toHex(int num) {
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            res.append(map[0xF & num]);
            num >>>= 4;
        }
        res = res.reverse();
        return res.toString().isEmpty() ? "0" : res.toString();
    }
}
