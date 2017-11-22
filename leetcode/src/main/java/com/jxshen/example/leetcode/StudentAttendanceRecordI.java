package com.jxshen.example.leetcode;

public class StudentAttendanceRecordI {

    public boolean checkRecord(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        char[] chars = s.toCharArray();
        int countA = 0;
        int countL = 0;
        int numL = 0;
        for (char c : chars) {
            if (c == 'A') {
                countA++;
            }
            if (c == 'L' && ++numL > 2) {
                countL++;
            }
            if (c != 'L') {
                numL = 0;
            }
        }
        return countA <= 1 && countL < 1;
    }
}
