package com.jxshen.example.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LongestWordInDictionary720 {

    public static void main(String[] args) {
        String[] words = {"a","banana","app","appl","ap","apply","apple"};
        System.out.println(new LongestWordInDictionary720().longestWord(words));
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word: words) {
            trie.insert(word, ++index);
        }
        trie.words = words;
        return trie.dfs();
    }

    static class Node {
        char c;
        Map<Character, Node> children = new HashMap<>();
        int end;

        public Node(char c) {
            this.c = c;
        }
    }

    static class Trie {
        Node root;
        String[] words;
        public Trie() {
            root = new Node('0');
        }
        public void insert(String word, int index) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }

        public String dfs() {
            String res = "";
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > res.length() || word.length() == res.length() && word.compareTo(res) < 0) {
                            res = word;
                        }
                    }
                    for (Node n : node.children.values()) {
                        stack.push(n);
                    }
                }
            }
            return res;
        }
    }
}
