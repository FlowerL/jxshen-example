package com.jxshen.example.leetcode;

public class PerfectNumber507 {

    public boolean checkPerfectNumber(int num) {
        if (num <= 0)
            return false;

        int sum = 0;
        int sqrt = (int)Math.sqrt(num);
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i < num) {
                    sum += num / i;
                }
            }
        }
        return num == sum - num;
    }
}
