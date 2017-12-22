package com.jxshen.example.algorithm.linklist;

public class LinkListReverse {

    public static void main(String[] args) {
        Node root = generate(new int[] {1,2,3,4,5,6,7});
        printLinkedList(root);
        root = reverse(root);
        printLinkedList(root);
        root = reverse2(root);
        printLinkedList(root);
    }

    private static Node generate(int[] nums) {
        Node root = new Node(nums[0]);
        Node cur = root;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new Node(nums[i]);
            cur = cur.next;
        }
        return root;
    }

    private static void printLinkedList(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println();
    }

    public static Node reverse(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node head = reverse(root.next);
        root.next.next = root;
        root.next = null;
        return head;
    }

    public static Node reverse2(Node root) {
        if (root == null || root.next == null) {
            return root;
        }

        Node newRoot = root;
        Node curNode = root;
        Node nextNode = root.next;
        while (nextNode != null) {
            curNode = nextNode;
            nextNode = nextNode.next;
            curNode.next = newRoot;
            if (newRoot == root) {
                newRoot.next = null;
            }
            newRoot = curNode;
        }
        return newRoot;
    }

    private static class Node {
        public Node(int val) {
            this.val = val;
        }

        int val;
        Node next;
    }
}
