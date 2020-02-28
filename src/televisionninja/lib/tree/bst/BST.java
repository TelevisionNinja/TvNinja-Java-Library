/**
 * 
 */
package televisionninja.lib.tree.bst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author TelevisionNinja
 *
 */
public class BST<E extends Comparable<E>> {
	// Inner class InorderIterator
	/**
	 * textbook method
	 * 
	 * @author TelevisionNinja
	 *
	 */
	private class InorderIterator implements Iterator<E> {
		// Store the elements in a list
		private final List<E> list = new ArrayList<>();
		private int current = 0; // Point to the current element in list

		/**
		 * textbook method
		 * 
		 */
		public InorderIterator() {
			inorder(); // Traverse binary tree and store elements in list
		}

		/**
		 * textbook method
		 * 
		 * More elements for traversing?
		 */
		@Override
		public boolean hasNext() {
			if (this.current < this.list.size()) {
				return true;
			}
			else {
				return false;
			}
		}

		/**
		 * textbook method
		 * 
		 * Inorder traversal from the root
		 */
		private void inorder() {
			inorder(BST.this.root);
		}

		/**
		 * textbook method
		 * 
		 * Inorder traversal from a subtree
		 */
		private void inorder(final BSTNode<E> root) {
			if (root != null) {
				inorder(root.left);
				this.list.add(root.element);
				inorder(root.right);
			}
		}

		/**
		 * textbook method
		 * 
		 * Get the current element and move to the next
		 */
		@Override
		public E next() {
			return this.list.get(this.current++);
		}

		/**
		 * textbook method
		 * 
		 * Remove the current element
		 */
		@Override
		public void remove() {
			delete(this.list.get(this.current)); // Delete the current element
			this.list.clear(); // Clear the list
			inorder(); // Rebuild the list
		}
	}

	private BSTNode<E> root;     // root of the BST

	private final List<E> outputList = new ArrayList<>();

	private long size = 0;

	/**
	 * Initializes an empty tree.
	 */
	public BST() {
	}

	/**
	 * creates copy of existing tree
	 * 
	 * @param tree
	 */
	public BST(final BST<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * textbook method
	 * 
	 * Create a binary search tree from an array of objects
	 */
	public BST(final E[] objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * 
	 * @param objects
	 */
	public BST(final List<E> objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * online method
	 * 
	 * Given a binary tree. Return its nodes in level order using array for implementing queue
	 */
	public List<E> breadthFirstOrder() {
		this.outputList.clear();
		final Queue<BSTNode<E>> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			/* poll() removes the present head. */
			final BSTNode<E> tempNode = queue.poll();

			this.outputList.add(tempNode.element);

			/* Enqueue left child */
			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			/* Enqueue right child */
			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}
		}
		return this.outputList;
	}

	/**
	 * textbook method
	 * 
	 * @author TelevisionNinja
	 */
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * textbook method
	 * 
	 * Delete an element from the binary search tree.
	 * Return true if the element is deleted successfully.
	 * Return false if the element is not in the tree.
	 */
	public boolean delete(final E e) {
		// Locate the node to be deleted and also locate its parent node
		BSTNode<E> parent = null,
				current = this.root;

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			}
			else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			}
			else {
				break; // Element is in the tree pointed at by current
			}
		}

		if (current == null) {
			return false; // Element is not in the tree
		}

		// Case 1: current has no left child
		if (current.left == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				this.root = current.right;
			}
			else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
				}
				else {
					parent.right = current.right;
				}
			}
		}
		else {
			// Case 2: The current node has a left child.
			// Locate the rightmost node in the left subtree of the current node and also its parent.
			BSTNode<E> parentOfRightMost = current,
					rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right; // Keep going to the right
			}

			// Replace the element in current by the element in rightMost
			current.element = rightMost.element;

			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			}
			else {
				// Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
			}
		}
		this.size--;
		return true; // Element deleted successfully
	}

	/**
	 * online method
	 * 
	 * Returns minimum distance beween a and b.
	 * This function assumes that a and b exist in BST.
	 * 
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 * @author TelevisionNinja
	 */
	private long distanceBetweenNodes(final BSTNode<E> root, final E a, final E b) {
		if (root != null) {
			// Both keys lie in left
			if (root.element.compareTo(a) > 0 && root.element.compareTo(b) > 0) {
				return distanceBetweenNodes(root.left, a, b);
			}
			// Both keys lie in right
			if (root.element.compareTo(a) < 0 && root.element.compareTo(b) < 0) {// same path
				return distanceBetweenNodes(root.right, a, b);
			}
			// Lie in opposite directions (Root is LCA of two nodes)
			if (root.element.compareTo(a) >= 0 && root.element.compareTo(b) <= 0) {
				return distanceFromRoot(root, a) + distanceFromRoot(root, b);
			}
		}
		return 0;
	}

	/**
	 * online method
	 * 
	 * This function make sure that a is smaller than b before making a call to findDistWrapper()
	 * 
	 * @param a
	 * @param b
	 * @return
	 * @author TelevisionNinja
	 */
	public long distanceBetweenNodes(E a, E b) {
		if (a.compareTo(b) > 0) {
			final E temp = a;
			a = b;
			b = temp;
		}
		return distanceBetweenNodes(this.root, a, b);
	}

	/**
	 * online method
	 * 
	 * This function returns distance of x from root. This function assumes that x exists in BST and BST is not NULL.
	 * 
	 * @param root
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	private long distanceFromRoot(final BSTNode<E> root, final E x) {
		if (root.element.compareTo(x) == 0) {
			return 0;
		}
		else if (root.element.compareTo(x) > 0) {
			return 1 + distanceFromRoot(root.left, x);
		}
		else {
			return 1 + distanceFromRoot(root.right, x);
		}
	}

	/**
	 * online method
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	public long distanceFromRoot(final E x) {
		return distanceFromRoot(this.root, x);
	}

	/**
	 * online method
	 * 
	 * Changes the tree by inserting a duplicate node on each nodes's .left.
	 * Uses a recursive helper to recur over the tree and insert the duplicates.
	 */
	public void doubleTree() {
		doubleTree(this.root);
	}

	/**
	 * online method
	 * 
	 * @param node
	 * @author TelevisionNinja
	 */
	private void doubleTree(final BSTNode<E> node) {
		BSTNode<E> oldLeft;
		if (node != null) {
			// do the subtrees
			doubleTree(node.left);
			doubleTree(node.right);
			// duplicate this node to its left
			oldLeft = node.left;
			node.left = new BSTNode<>(node.element);
			node.left.left = oldLeft;
		}
	}

	/**
	 * online method
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public BSTNode<E> getRoot() {
		return this.root;
	}

	/**
	 * textbook method
	 * 
	 * Get the number of nodes in the tree
	 */
	public long getSize() {
		return this.size;
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumByte(final BSTNode<Byte> node, final long sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final long subSum = sum - node.element;
			return hasPathSumByte(node.left, subSum) || hasPathSumByte(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumCharacter(final BSTNode<Character> node, final long sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final long subSum = sum - node.element;
			return hasPathSumCharacter(node.left, subSum) || hasPathSumCharacter(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumDouble(final BSTNode<Double> node, final double sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final double subSum = sum - node.element;
			return hasPathSumDouble(node.left, subSum) || hasPathSumDouble(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumFloat(final BSTNode<Float> node, final float sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final float subSum = sum - node.element;
			return hasPathSumFloat(node.left, subSum) || hasPathSumFloat(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumInteger(final BSTNode<Integer> node, final long sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final long subSum = sum - node.element;
			return hasPathSumInteger(node.left, subSum) || hasPathSumInteger(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumLong(final BSTNode<Long> node, final long sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final long subSum = sum - node.element;
			return hasPathSumLong(node.left, subSum) || hasPathSumLong(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Given a tree and a sum, returns true if there is a path from the root down to a leaf, such that adding up all the values along the path equals the given sum.
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.
	 */
	public boolean hasPathSumShort(final BSTNode<Short> node, final long sum) {
		// return true if we run out of tree and sum == 0
		if (node == null) {
			return sum == 0;
		}
		else {
			// otherwise check both subtrees
			final long subSum = sum - node.element;
			return hasPathSumShort(node.left, subSum) || hasPathSumShort(node.right, subSum);
		}
	}

	/**
	 * online method
	 * 
	 * Returns the height of a tree which is the number of nodes along the longest path from the root node down to the farthest leaf node.
	 */
	public long height() {
		return height(this.root);
	}

	/**
	 * online method
	 * 
	 * Compute the height of a tree which is the number of nodes along the longest path from the root node down to the farthest leaf node.
	 */
	private long height(final BSTNode<E> root) {
		if (root == null) {
			return 0;
		}
		else {
			/* compute  height of each subtree */
			final long lheight = height(root.left),
					rheight = height(root.right);

			/* use the larger one */
			if (lheight > rheight) {
				return lheight + 1;
			}
			else {
				return rheight + 1;
			}
		}
	}

	/**
	 * textbook method
	 * 
	 * Inorder traversal from the root
	 */
	public List<E> inorder() {
		this.outputList.clear();
		inorder(this.root);
		return this.outputList;
	}

	/**
	 * textbook method
	 * 
	 * Inorder traversal from a subtree
	 */
	private void inorder(final BSTNode<E> root) {
		if (root != null) {
			inorder(root.left);
			this.outputList.add(root.element);
			inorder(root.right);
		}
	}

	/**
	 * textbook method
	 * 
	 * Insert element e into the binary search tree.
	 * Return true if the element is inserted successfully.
	 */
	public boolean insert(final E e) {
		if (this.root == null) {
			this.root = new BSTNode<>(e); // Create a new root
		}
		else {
			// Locate the parent node
			BSTNode<E> parent = null,
					current = this.root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				}
				else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				}
				else {
					return false; // Duplicate node not inserted
				}
			}
			// Create the new node and attach it to the parent node
			if (e.compareTo(parent.element) < 0) {
				parent.left = new BSTNode<>(e);
			}
			else {
				parent.right = new BSTNode<>(e);
			}
		}
		this.size++;
		return true; // Element inserted successfully
	}

	/**
	 * online method
	 * 
	 * Is this tree empty?
	 * 
	 * @return {@code true} if this tree is empty and {@code false} otherwise
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * textbook method
	 * 
	 * Obtain an iterator. Use inorder.
	 */
	public Iterator<E> iterator() {
		return new InorderIterator();
	}

	/**
	 * online method
	 * 
	 * @author TelevisionNinja
	 */
	public void leafDelete() {
		leafDelete(this.root);
	}

	/**
	 * online method
	 * 
	 * Delete leaf nodes from binary search tree.
	 * @param root
	 * @return
	 * @author TelevisionNinja
	 */
	private BSTNode<E> leafDelete(final BSTNode<E> root) {
		if (root.left == null && root.right == null) {
			return null;
		}
		else {
			// Else recursively delete in left and right subtrees.
			root.left = leafDelete(root.left);
			root.right = leafDelete(root.right);
			return root;
		}
	}

	/**
	 * online method
	 * 
	 * Returns the max value in a non-empty binary search tree.
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public E maxValue() {
		BSTNode<E> current = this.root;
		while (current.right != null) {
			current = current.right;
		}
		return(current.element);
	}

	/**
	 * online method
	 * 
	 * Returns the node with the max value at a given root
	 * 
	 * @param root
	 * @return
	 * @author TelevisionNinja
	 */
	public BSTNode<E> maxValue(BSTNode<E> root) {
		while (root.right != null) {
			root = root.right;
		}
		return(root);
	}

	/**
	 * online method
	 * 
	 * merges the input tree with this tree
	 * 
	 * @param tree
	 */
	public void merge(final BST<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * online method
	 * 
	 * @param root
	 * @author TelevisionNinja
	 */
	private void merge(final BSTNode<E> node) {
		if (node != null) {
			insert(node.element);
			merge(node.left);
			merge(node.right);
		}
	}

	/**
	 * online method
	 * 
	 * Returns the min value in a non-empty binary search tree.
	 */
	public E minValue() {
		BSTNode<E> current = this.root;
		while (current.left != null) {
			current = current.left;
		}
		return(current.element);
	}

	/**
	 * online method
	 * 
	 * Returns the node with the min value at a given root
	 * 
	 * @param root
	 * @return
	 * @author TelevisionNinja
	 */
	public BSTNode<E> minValue(BSTNode<E> root) {
		while (root.left != null) {
			root = root.left;
		}
		return(root);
	}

	/**
	 * online method
	 * 
	 * Changes the tree into its mirror image.
	 * Uses a recursive helper that recurs over the tree, swapping the left/right pointers.
	 */
	public void mirror() {
		mirror(this.root);
	}

	/**
	 * online method
	 * 
	 * @param node
	 * @author TelevisionNinja
	 */
	private void mirror(final BSTNode<E> node) {
		if (node != null) {
			// do the sub-trees
			mirror(node.left);
			mirror(node.right);
			// swap the left/right pointers
			final BSTNode<E> temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}

	/**
	 * online method
	 * 
	 * list of nodes at the given level
	 */
	private void nodesAtGivenLevel(final BSTNode<E> root, final long level) {
		if (root != null) {
			if (level == 1) {
				this.outputList.add(root.element);
			}
			else if (level > 1) {
				nodesAtGivenLevel(root.left, level - 1);
				nodesAtGivenLevel(root.right, level - 1);
			}
		}

	}

	/**
	 * online method
	 * 
	 * list of nodes at the given level
	 */
	public List<E> nodesAtGivenLevel(final long level) {
		this.outputList.clear();
		nodesAtGivenLevel(this.root, level);
		return this.outputList;
	}

	/**
	 * online method
	 * 
	 * The functions prints all the keys which in the given range [k1..k2].
	 * The function assumes than k1 < k2
	 * 
	 * @param node
	 * @param k1
	 * @param k2
	 * @author TelevisionNinja
	 */
	private void nodesWithinGivenRange(final BSTNode<E> node, final E k1, final E k2) {
		/* base case */
		if (node != null) {
			/* Since the desired o/p is sorted, recurse for left subtree first
			 * If root->data is greater than k1, then only we can get o/p keys in left subtree
			 */
			if (node.element.compareTo(k1) > 0) {
				nodesWithinGivenRange(node.left, k1, k2);
			}

			/* if root's data lies in range, then prints root's data */
			if (node.element.compareTo(k1) >= 0 && node.element.compareTo(k2) <= 0) {
				this.outputList.add(node.element);
			}

			/* If root->data is smaller than k2, then only we can get o/p keys in right subtree */
			if (node.element.compareTo(k2) < 0) {
				nodesWithinGivenRange(node.right, k1, k2);
			}
		}
	}

	/**
	 * online method
	 * 
	 * @param k1
	 * @param k2
	 * @return
	 * 		- list of nodes within the given range
	 * @author TelevisionNinja
	 */
	public List<E> nodesWithinGivenRange(final E k1, final E k2) {
		this.outputList.clear();
		nodesWithinGivenRange(this.root, k1, k2);
		return this.outputList;
	}

	/**
	 * textbook method
	 * 
	 * Returns a path from the root leading to the specified element
	 */
	public List<BSTNode<E>> path(final E e) {
		final List<BSTNode<E>> list = new ArrayList<>();
		BSTNode<E> current = this.root; // Start from the root
		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left; // Add the node to the list
			}
			else if (e.compareTo(current.element) > 0) {
				current = current.right;
			}
			else {
				break;
			}
		}
		return list; // Return an array list of nodes
	}

	/**
	 * textbook method
	 * 
	 * Preorder traversal from the root
	 */
	public List<E> postorder() {
		this.outputList.clear();
		postorder(this.root);
		return this.outputList;
	}

	/**
	 * textbook method
	 * 
	 * Postorder traversal from a subtree
	 */
	private void postorder(final BSTNode<E> root) {
		if (root != null) {
			postorder(root.left);
			postorder(root.right);
			this.outputList.add(root.element);
		}
	}

	/**
	 * textbook method
	 * 
	 * Postorder traversal from the root
	 */
	public List<E> preorder() {
		this.outputList.clear();
		preorder(this.root);
		return this.outputList;
	}

	/**
	 * textbook method
	 * 
	 * Preorder traversal from a subtree
	 */
	private void preorder(final BSTNode<E> root) {
		if (root != null) {
			this.outputList.add(root.element);
			preorder(root.left);
			preorder(root.right);
		}
	}

	/**
	 * online method
	 * 
	 * Compares the receiver to another tree to see if they are structurally identical.
	 */
	public boolean sameTree(final BST<E> other) {
		return sameTree(this.root, other.getRoot());
	}

	/**
	 * online method
	 * 
	 * Recursive helper -- recurs down two trees in parallel, checking to see if they are identical.
	 */
	private boolean sameTree(final BSTNode<E> a, final BSTNode<E> b) {
		// 1. both empty -> true
		if (a == null && b == null) {
			return true;
		}
		// 2. both non-empty -> compare them
		else if (a != null && b != null) {
			return a.element.equals(b.element) &&
					sameTree(a.left, b.left) &&
					sameTree(a.right, b.right);
		}
		// 3. one empty, one not -> false
		else {
			return false;
		}
	}

	/**
	 * textbook method
	 * 
	 * Return true if the element is in the tree
	 */
	public boolean search(final E e) {
		BSTNode<E> current = this.root; // Start from the root
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			}
			else if (e.compareTo(current.element) > 0) {
				current = current.right;
			}
			else { // element matches current.element
				return true; // Element is found
			}
		}
		return false;
	}
}