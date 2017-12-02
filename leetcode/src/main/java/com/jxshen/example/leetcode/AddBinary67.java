package com.jxshen.example.leetcode;

public class AddBinary67 {

    public static void main(String[] args) {
        System.out.println(addBinary("1111", "1111"));
    }

    public static String addBinary(String a, String b) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) {
            return new String();
        }

        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carrier = 0;
        int sum = 0;
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                sum += aChars[i] - '0';
                i--;
            }
            if (j >= 0) {
                sum += bChars[j] - '0';
                j--;
            }
            sum += carrier;
            switch (sum) {
                case 0:
                    sb.append(0);
                    break;
                case 1:
                    sb.append(1);
                    carrier = 0;
                    break;
                case 2:
                    sb.append(0);
                    carrier = 1;
                    break;
                case 3:
                    sb.append(1);
                    carrier = 1;
                    break;
            }
            sum = 0;
        }
        if (carrier != 0) {
            sb.append(carrier);
        }
        return sb.reverse().toString();
    }
}
