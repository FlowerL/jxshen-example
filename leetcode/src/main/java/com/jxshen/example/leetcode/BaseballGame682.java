package com.jxshen.example.leetcode;

import java.util.LinkedList;
import java.util.List;

public class BaseballGame682 {

    public int calPoints(String[] ops) {
        if (ops == null || ops.length <= 0) {
            return 0;
        }

        int res = 0;
        List<Integer> valids = new LinkedList<>();
        int lastValidIndex = -1;
        for (String op : ops) {
            switch (op) {
                case "+":
                    valids.add(getLastValid(lastValidIndex, valids) + getLastNextValid(lastValidIndex, valids));
                    lastValidIndex++;
                    res += valids.get(lastValidIndex);
                    break;
                case "D":
                    valids.add(getLastValid(lastValidIndex, valids) * 2);
                    lastValidIndex++;
                    res += valids.get(lastValidIndex);
                    break;
                case "C":
                    int removedValid = removeLastValid(lastValidIndex, valids);
                    lastValidIndex--;
                    res -= removedValid;
                    break;
                default:
                    valids.add(Integer.parseInt(op));
                    lastValidIndex++;
                    res += valids.get(lastValidIndex);
                    break;
            }
        }
        return res;
    }

    private int getLastValid(int lastValidIndex, List<Integer> valids) {
        return lastValidIndex < 0 ? 0 : valids.get(lastValidIndex);
    }

    private int getLastNextValid(int lastValidIndex, List<Integer> valids) {
        return lastValidIndex-- < 0 ? 0 : valids.get(lastValidIndex);
    }

    private int removeLastValid(int lastValidIndex, List<Integer> valids) {
        if (lastValidIndex < 0 || lastValidIndex >= valids.size()) {
            return 0;
        }
        return valids.remove(lastValidIndex);
    }
}
