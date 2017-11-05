package com.jxshen.example.leetcode;

import java.util.Arrays;

public class OneBitAndTwoBitCharacters717 {
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 1) {
            return true;
        }
        if (bits.length == 2 && bits[0] == 1) {
            return false;
        }
        if (bits.length == 2 && bits[0] == 0) {
            return true;
        }
        int[] subBits = bits[0] == 0 ? Arrays.copyOfRange(bits, 1, bits.length)
                : Arrays.copyOfRange(bits, 2, bits.length);
        return isOneBitCharacter(subBits);
    }
}
