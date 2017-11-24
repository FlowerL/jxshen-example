package com.jxshen.example.leetcode;

public class AddStrings415 {

    public static void main(String[] args) {
        System.out.println(addStrings("9", "1"));
    }

    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            return num2;
        }
        if (num2 == null || num2.isEmpty()) {
            return num1;
        }

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int length1 = chars1.length;
        int length2 = chars2.length;
        int length = length1 > length2 ? length1 : length2;
        int carrier = 0;
        int tmp1 = 0;
        int tmp2 = 0;
        int sum = 0;
        int index1 = 0;
        int index2 = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < length; i++) {
            index1 = length1 - 1 - i;
            index2 = length2 - 1 - i;
            if (index1 < 0) {
                tmp1 = 0;
                tmp2 = chars2[index2] - '0';
            }
            if (index2 < 0) {
                tmp1 = chars1[index1] - '0';
                tmp2 = 0;
            }
            if (index1 >= 0 && index2 >= 0) {
                tmp1 = chars1[index1] - '0';
                tmp2 = chars2[index2] - '0';
            }
            sum = tmp1 + tmp2 + carrier;
            if (sum > 9) {
                carrier = 1;
                sum = sum % 10;
            }
            else {
                carrier = 0;
            }
            res.append(sum);
        }
        if (carrier > 0) {
            res.append(carrier);
        }
        return res.reverse().toString();
    }
}
