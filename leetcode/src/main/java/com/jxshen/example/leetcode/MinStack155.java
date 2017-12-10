package com.jxshen.example.leetcode;

import java.util.Stack;

public class MinStack155 {

    private Stack<Long> stack;
    private long min;

    /** initialize your data structure here. */
    public MinStack155() {
        stack = new Stack();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        }
        else {
            stack.push(x - min);
            min = x < min ? x : min;
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        long gap = stack.pop();
        if (gap < 0) {
            min -= gap;
        }
    }

    public int top() {
        long gap = stack.peek();
        if (gap > 0) {
            return (int)(gap + min);
        }
        else {
            return (int)min;
        }
    }

    public int getMin() {
        return (int)min;
    }
}
