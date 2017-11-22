package com.jxshen.example.leetcode;

public class BaseSeven504 {

    public String convertToBase7(int num) {
        boolean isNegetive = num < 0;
        StringBuilder sb = new StringBuilder();
        num = Math.abs(num);
        do {
            sb.append(num % 7);
        } while ((num /= 7) != 0);
        sb.append(isNegetive ? "-" : "");
        return sb.reverse().toString();
    }

}
