package televisionninja.lib.datastructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList; // FIFO queue
import java.util.ArrayList;

public class AhoCorasick {
	protected class AhoCorasickNode {
		public HashMap<Character, AhoCorasickNode> children; // can be replaced with array if alphabet is known
        public AhoCorasickNode suffixLink;
        public HashSet<AhoCorasickNode> outputLinks;
        public int length;

        public AhoCorasickNode() {
            this.suffixLink = null;
            this.length = 0;
            
            this.children = new HashMap<>();
            this.suffixLink = null;
            this.outputLinks = new HashSet<>();
        }
	}

	protected AhoCorasickNode root;

	protected boolean deleteTrieNode(AhoCorasickNode node, final String word, int depth) {
	    if (depth == word.length()) {
	        if (node.length == 0) {
	            return false;
	        }

	        node.length = 0;
	        return node.children.isEmpty();
	    }

	    char c = word.charAt(depth);

	    if (!node.children.containsKey(c)) {
	        return false;
	    }

	    boolean shouldDeleteChild = this.deleteTrieNode(node.children.get(c), word, depth + 1);

	    if (shouldDeleteChild) {
	    	// delete node.children.get(c);
	        node.children.remove(c);
	        return node.children.isEmpty() && node.length == 0;
	    }

	    return false;
	}
	
	public AhoCorasick() {
	    this.root = new AhoCorasickNode();
	}

	public AhoCorasick(final ArrayList<String> strings) {
	    this.build(strings);
	}
	
	public void build(final ArrayList<String> strings) {
	    this.root = new AhoCorasickNode();

	    for (int i = 0; i < strings.size(); i++) {
	        this.insertTrie(strings.get(i));
	    }

	    this.constructLinks();
	}

	public void deconstructor() {
	    this.freeTrieNode(this.root);
	}

	protected void insertTrie(final String word) {
	    AhoCorasickNode node = this.root;
	    int i = 0;

	    while (i < word.length()) {
	        char c = word.charAt(i);
	        i++;

	        if (!node.children.containsKey(c)) {
	        	node.children.put(c, new AhoCorasickNode());
	        }

	        node = node.children.get(c);
	    }

	    node.outputLinks.add(node);
	    node.length = word.length(); // height at the node is the length of the string
	}

	public void insert(final String word) {
	    this.insertTrie(word);
	    this.constructLinks();
	}

	/**
	 * returns [tuple(index, length), ...]
	 */
	public ArrayList<int[]> search(final String string) {
	    AhoCorasickNode node = this.root;
	    ArrayList<int[]> output = new ArrayList<>();

	    // empty string case
	    // only the root's output set's size is check because the empty string has no length
	    if (!node.outputLinks.isEmpty()) {
	        output.add(new int[] {0, 0}); // no need to iterate through output links
	    }

	    int i = 0;

	    while (i < string.length()) {
	        final char c = string.charAt(i);
	        if (node.children.containsKey(c)) {
	            node = node.children.get(c);
	            i++;

	            if (!node.outputLinks.isEmpty()) {
	                for (AhoCorasickNode outputNode : node.outputLinks) { // node.outputLinks.values()
	                    output.add(new int[] {i - outputNode.length, outputNode.length});
	                }
	            }
	        }
	        else if (node == this.root) {
	            i++;
	        }
	        else {
	            node = node.suffixLink;
	        }
	    }

	    return output;
	}

	public void remove(final String word) {
	    this.deleteTrieNode(this.root, word, 0);
	    this.deleteLinks();
	    this.constructLinks();
	}

	protected void freeTrieNode(AhoCorasickNode node) {
	    for (AhoCorasickNode value : node.children.values()) { // node.children.values()
	        this.freeTrieNode(value);
	    }

	    node = null; // delete node;
	}

	protected void constructLinks() {
	    // BFS
	    Queue<AhoCorasickNode> nodeQueue = new LinkedList<>();

	    for (AhoCorasickNode value : this.root.children.values()) { // node.children.values()
	        nodeQueue.add(value);
	        value.suffixLink = this.root;
	    }

	    while (!nodeQueue.isEmpty()) {
	        AhoCorasickNode currentNode = nodeQueue.element();
	        nodeQueue.remove();

	        for (HashMap.Entry<Character, AhoCorasickNode> entry : currentNode.children.entrySet()) { // node.children.values()            KKKEEEEEYYYYYY
	            Character key = entry.getKey();
	        	AhoCorasickNode value = entry.getValue();

	        	nodeQueue.add(value);

	            // output links
	            AhoCorasickNode childFailureNode = currentNode.suffixLink;
	            while (childFailureNode != null && !childFailureNode.children.containsKey(key)) {
	                childFailureNode = childFailureNode.suffixLink;
	            }

	            if (childFailureNode != null) {
	                value.suffixLink = childFailureNode.children.get(key);
	            }
	            else {
	                value.suffixLink = this.root;
	            }

	            if (value.suffixLink.length != 0) {
	                value.outputLinks.addAll(value.suffixLink.outputLinks); // set1 U copy(set2)
	            }
	        }
	    }
	}

	protected void deleteLinks() {
	    // DFS because of stack implementation time complexity. traversal order does not matter
	    Stack<AhoCorasickNode> nodeStack = new Stack<>();
	    nodeStack.push(this.root);

	    while (!nodeStack.empty()) {
	        AhoCorasickNode currentNode = nodeStack.peek();
	        nodeStack.pop();

	        // clear out all links because some are pointing to the deleted nodes
	        currentNode.suffixLink = null;
	        currentNode.outputLinks.clear();

	        if (currentNode.length != 0) {
	            currentNode.outputLinks.add(currentNode);
	        }

	        for (AhoCorasickNode value : currentNode.children.values()) { // node.children.values()
	            nodeStack.push(value);
	        }
	    }
	}
}
