package com.jxshen.example.leetcode;

public class SumOfSquareNumbers633 {

    public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }

        for (long a = 0; a * a <= c; a++) {
            int b = c - (int)(a * a);
            if (binarySearch(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(long begin, long end, int target) {
        if (begin > end) {
            return false;
        }
        long mid = (begin + end) / 2;
        long sqrtMid = mid * mid;
        if (sqrtMid == target) {
            return true;
        }
        else if (sqrtMid > target) {
            return binarySearch(begin, mid-1, target);
        }
        else {
            return binarySearch(mid+1, end, target);
        }
    }
}
