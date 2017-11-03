package com.jxshen.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz412 {

    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";

    public List<String> fizzBuzz(int n) {
        if (n <= 0) {
            return null;
        }

        List<String> res = new ArrayList<>();
        int count3 = 0;
        int count5 = 0;
        String eachRes = "";
        for (int i = 1; i <= n; i++) {
            count3++;
            count5++;
            if (count3 == 3) {
                eachRes += FIZZ;
                count3 = 0;
            }
            if (count5 == 5) {
                eachRes += BUZZ;
                count5 = 0;
            }
            if (eachRes.isEmpty()) {
                eachRes = String.valueOf(i);
            }
            res.add(eachRes);
            eachRes = "";
        }
        return res;
    }
}
