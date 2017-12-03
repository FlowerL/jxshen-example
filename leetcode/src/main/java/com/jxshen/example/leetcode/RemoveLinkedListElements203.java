package com.jxshen.example.leetcode;

public class RemoveLinkedListElements203 {

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }

        ListNode curr = head.next;
        ListNode pre = head;
        while (curr != null) {
            if (curr.val == val) {
                curr = curr.next;
                pre.next = curr;
            }
            else {
                curr = curr.next;
                pre = pre.next;
            }
        }

        return head;
    }
}
