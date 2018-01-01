package com.jxshen.example.leetcode;

public class ComplexNumberMultiplication537 {

    public static void main(String[] args) {
        System.out.println(new ComplexNumberMultiplication537().complexNumberMultiply("1+1i", "1+1i"));
    }

    public String complexNumberMultiply(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }

        int[] compA = parseComplexity2Array(a);
        int[] compB = parseComplexity2Array(b);
        int[] multi = new int[2];
        multi[0] = compA[0] * compB[0] - compA[1] * compB[1];
        multi[1] = compA[0] * compB[1] + compA[1] * compB[0];
        return parseArray2Complexity(multi);
    }

    private int[] parseComplexity2Array(String str) {
        int[] res = new int[2];
        String[] comps = str.split("\\+");
        res[0] = Integer.parseInt(comps[0]);
        res[1] = Integer.parseInt(comps[1].substring(0, comps[1].length() - 1));
        return res;
    }

    private String parseArray2Complexity(int[] comp) {
        StringBuilder sb = new StringBuilder();
        sb.append(comp[0]);
        sb.append("+");
        sb.append(comp[1]);
        sb.append("i");
        return sb.toString();
    }
}
