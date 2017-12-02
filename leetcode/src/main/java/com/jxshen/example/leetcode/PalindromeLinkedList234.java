package com.jxshen.example.leetcode;

public class PalindromeLinkedList234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode rHead = reverse(head);
        while (head != null) {
            if (head.val != rHead.val) {
                return false;
            }
            head = head.next;
            rHead = rHead.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode rHead = null;
        ListNode pre = null;
        while (head != null) {
            pre = new ListNode(head.val);
            pre.next = rHead;
            rHead = pre;
            head = head.next;
        }
        return rHead;
    }
}
