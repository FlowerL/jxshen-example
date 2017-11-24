package com.jxshen.example.algorithm.linklist;

public class LinkListReverse {

    public static Node reverse(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node head = reverse(root.next);
        root.next.next = root;
        root.next = null;
        return head;
    }

    private static class Node {
        Node next;
    }
}
