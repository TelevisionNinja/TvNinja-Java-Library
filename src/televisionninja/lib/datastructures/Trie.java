package televisionninja.lib.datastructures;

import java.util.HashMap;

public class Trie {
    private class TrieNode {
        public HashMap<Character, TrieNode> children; // can be replaced with array if alphabet is known
        public boolean isEndOfWord;

        TrieNode() {
        	this.children = new HashMap<>();
        	this.isEndOfWord = false;
        }
    }

    private boolean deleteNode(TrieNode node, final String word, int depth) {
        if (depth == word.length()) {
            if (!node.isEndOfWord) {
                return false;
            }

            node.isEndOfWord = false;
            return node.children.isEmpty();
        }

        char c = word.charAt(depth);

        if (!node.children.containsKey(c)) {
            return false;
        }

        boolean shouldDeleteChild = this.deleteNode(node.children.get(c), word, depth + 1);

        if (shouldDeleteChild) {
        	// delete node.children.get(c);
            node.children.remove(c);
            return node.children.isEmpty() && !node.isEndOfWord;
        }

        return false;
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void deconstructor() {
        this.freeNode(this.root);
    }

    public void insert(final String word) {
        TrieNode node = this.root;

        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);

            if (!node.children.containsKey(c)) {
            	node.children.put(c, new TrieNode());
            }

            node = node.children.get(c);
        }

        node.isEndOfWord = true;
    }

    public boolean search(final String word) {
        TrieNode node = this.root;

        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);

            if (!node.children.containsKey(c)) {
                return false;
            }

            node = node.children.get(c);
        }

        return node.isEndOfWord;
    }

    public boolean startsWith(final String prefix) {
        TrieNode node = this.root;

        for (int i = 0; i < prefix.length(); i++) {
        	char c = prefix.charAt(i);

            if (!node.children.containsKey(c)) {
                return false;
            }

            node = node.children.get(c);
        }

        return true;
    }

    public void remove(final String word) {
        this.deleteNode(this.root, word, 0);
    }

    private void freeNode(TrieNode node) {
        for (TrieNode value : node.children.values()) { // node.children.values()
            this.freeNode(value);
        }

        node = null; // delete node;
    }
}
