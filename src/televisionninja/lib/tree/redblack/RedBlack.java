/**
 * 
 */
package televisionninja.lib.tree.redblack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author TelevisionNinja
 *
 */
public class RedBlack<E extends Comparable<E>> {
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
			inorder(RedBlack.this.root);
		}

		/**
		 * textbook method
		 * 
		 * Inorder traversal from a subtree
		 */
		private void inorder(final RedBlackNode<E> root) {
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

	private static final boolean red = true,
			black = false;

	private RedBlackNode<E> root;     // root of the BST

	private final List<E> outputList = new ArrayList<>();

	private long size = 0;

	/**
	 * Initializes an empty tree
	 */
	public RedBlack() {
	}

	/**
	 * textbook method
	 * 
	 * Create a binary search tree from an array of objects
	 */
	public RedBlack(final E[] objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * 
	 * @param objects
	 */
	public RedBlack(final List<E> objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * creates copy of existing tree
	 * 
	 * @param tree
	 */
	public RedBlack(final RedBlack<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * restore red-black tree invariant
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> balance(RedBlackNode<E> h) {
		// assert (h != null);

		if (isRed(h.right)) {
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}

		h.size = numberOfNodes(h.left) + numberOfNodes(h.right) + 1;
		return h;
	}

	/**
	 * online method
	 * 
	 * Given a binary tree. Return its nodes in level order using array for implementing queue
	 */
	public List<E> breadthFirstOrder() {
		this.outputList.clear();
		final Queue<RedBlackNode<E>> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			/* poll() removes the present head. */
			final RedBlackNode<E> tempNode = queue.poll();

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
	 * online method
	 * 
	 * Removes the specified element from this tree
	 * (if the element is in this tree).
	 *
	 * @param element
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(final E element) {
		if (element == null) {
			System.out.println("argument to delete() is null");
		}
		if (!search(element)) {
			return;
		}

		// if both children of root are black, set root to red
		if (!isRed(this.root.left) && !isRed(this.root.right)) {
			this.root.color = red;
		}

		this.root = delete(this.root, element);
		if (!isEmpty()) {
			this.root.color = black;
			// assert check();
		}
	}

	/**
	 * online method
	 * 
	 * delete the given element rooted at h
	 * 
	 * @param h
	 * @param key
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> delete(RedBlackNode<E> h, final E element) {
		// assert get(h, key) != null;

		if (element.compareTo(h.element) < 0)  {
			if (!isRed(h.left) && !isRed(h.left.left)) {
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, element);
		}
		else {
			if (isRed(h.left)) {
				h = rotateRight(h);
			}
			if (element.compareTo(h.element) == 0 && (h.right == null)) {
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left)) {
				h = moveRedRight(h);
			}
			if (element.compareTo(h.element) == 0) {
				final RedBlackNode<E> x = minValue(h.right);
				h.element = x.element;
				// h.val = get(h.right, min(h.right).key);
				// h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			}
			else {
				h.right = delete(h.right, element);
			}
		}
		return balance(h);
	}

	/**
	 * online method
	 * 
	 * Removes the largest element from the tree
	 */
	public void deleteMax() {
		// if both children of root are black, set root to red
		if (!isRed(this.root.left) && !isRed(this.root.right)) {
			this.root.color = red;
		}

		this.root = deleteMax(this.root);
		if (!isEmpty())
		{
			this.root.color = black;
			// assert check();
		}
	}

	/**
	 * online method
	 * 
	 * delete the maximum element rooted at h
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> deleteMax(RedBlackNode<E> h) {
		if (isRed(h.left)) {
			h = rotateRight(h);
		}

		if (h.right == null) {
			return null;
		}

		if (!isRed(h.right) && !isRed(h.right.left)) {
			h = moveRedRight(h);
		}

		h.right = deleteMax(h.right);

		return balance(h);
	}

	/**
	 * online method
	 * 
	 * Removes the smallest element from the tree
	 */
	public void deleteMin() {
		// if both children of root are black, set root to red
		if (!isRed(this.root.left) && !isRed(this.root.right)) {
			this.root.color = red;
		}

		this.root = deleteMin(this.root);
		if (!isEmpty()) {
			this.root.color = black;
			// assert check();
		}
	}

	/**
	 * online method
	 * 
	 * delete the element with the minimum key rooted at h
	 *
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> deleteMin(RedBlackNode<E> h) {
		if (h.left == null) {
			return null;
		}

		if (!isRed(h.left) && !isRed(h.left.left)) {
			h = moveRedLeft(h);
		}

		h.left = deleteMin(h.left);
		return balance(h);
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
	 * Returns minimum distance beween a and b.
	 * This function assumes that a and b exist in BST.
	 * 
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 * @author TelevisionNinja
	 */
	private long distanceBetweenNodes(final RedBlackNode<E> root, final E a, final E b) {
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
	 * This function returns distance of x from root. This function assumes that x exists in BST and BST is not NULL.
	 * 
	 * @param root
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	private long distanceFromRoot(final RedBlackNode<E> root, final E x) {
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
	private void doubleTree(final RedBlackNode<E> node) {
		RedBlackNode<E> oldLeft;
		if (node != null) {
			// do the subtrees
			doubleTree(node.left);
			doubleTree(node.right);
			// duplicate this node to its left
			oldLeft = node.left;
			node.left = new RedBlackNode<>(node.element);
			node.left.left = oldLeft;
		}
	}

	/**
	 * online method
	 * 
	 * flip the colors of a node and its two children
	 * 
	 * @param h
	 * @author TelevisionNinja
	 */
	private void flipColors(final RedBlackNode<E> h) {
		// h must have opposite color of its two children
		// assert (h != null) && (h.left != null) && (h.right != null);
		// assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
		//    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	/**
	 * online method
	 * 
	 * Return the element in the tree whose rank is {@code k}.
	 * This is the (k + 1)st smallest key in the tree
	 *
	 * @param index the order statistic
	 * @return the key in the tree of rank {@code k}
	 */
	public E get(final int index) {
		if (index >= 0 && index < numberOfNodes()) {
			return get(this.root, index).element;
		}
		else {
			return null;
		}
	}

	/**
	 * online method
	 * 
	 * the element of rank k in the subtree rooted at x
	 * 
	 * @param x
	 * @param index
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> get(final RedBlackNode<E> x, final int index) {
		// assert x != null;
		// assert k >= 0 && k < size(x);
		final int t = numberOfNodes(x.left);

		if (t > index) {
			return get(x.left, index);
		}
		else if (t < index) {
			return get(x.right, index - t - 1);
		}
		else {
			return x;
		}
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public RedBlackNode<E> getRoot() {
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
	public boolean hasPathSumByte(final RedBlackNode<Byte> node, final long sum) {
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
	public boolean hasPathSumCharacter(final RedBlackNode<Character> node, final long sum) {
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
	public boolean hasPathSumDouble(final RedBlackNode<Double> node, final double sum) {
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
	public boolean hasPathSumFloat(final RedBlackNode<Float> node, final float sum) {
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
	public boolean hasPathSumInteger(final RedBlackNode<Integer> node, final long sum) {
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
	public boolean hasPathSumLong(final RedBlackNode<Long> node, final long sum) {
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
	public boolean hasPathSumShort(final RedBlackNode<Short> node, final long sum) {
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
	private long height(final RedBlackNode<E> root) {
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
	 * online method
	 * 
	 * Return the number of elements in the tree strictly less than {@code key}
	 * 
	 * @param element the key
	 * @return the number of elements in the tree strictly less than {@code key}
	 */
	public int indexOf(final E element) {
		return indexOf(element, this.root);
	}

	/**
	 * online method
	 * 
	 * number of elements less than element in the subtree rooted at x
	 * 
	 * @param element
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	private int indexOf(final E element, final RedBlackNode<E> x) {
		if (x == null) {
			return 0;
		}
		final int cmp = element.compareTo(x.element);
		if (cmp < 0) {
			return indexOf(element, x.left);
		}
		else if (cmp > 0) {
			return 1 + numberOfNodes(x.left) + indexOf(element, x.right);
		}
		else {
			return numberOfNodes(x.left);
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
	private void inorder(final RedBlackNode<E> root) {
		if (root != null) {
			inorder(root.left);
			this.outputList.add(root.element);
			inorder(root.right);
		}
	}

	/**
	 * online method
	 * 
	 * Inserts the specified element into the tree
	 *
	 * @param key the key
	 * @param val the value
	 */
	public void insert(final E element) {
		if (element == null) {
			System.out.println("argument to put() is null");
		}
		else {
			this.root = insert(this.root, element);
			this.root.color = black;
		}
	}

	/**
	 * online method
	 * 
	 * insert the element in the subtree rooted at h
	 * 
	 * @param h
	 * @param key
	 * @param val
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> insert(RedBlackNode<E> h, final E element) {
		if (h == null) {
			return new RedBlackNode<>(element);
		}

		final int cmp = element.compareTo(h.element);
		if (cmp < 0) {
			h.left = insert(h.left, element);
		}
		else if (cmp > 0) {
			h.right = insert(h.right, element);
		}
		else {
			h.element = element;
		}

		// fix-up any right-leaning links
		if (isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		if (isRed(h.left)  &&  isRed(h.left.left)) {
			h = rotateRight(h);
		}
		if (isRed(h.left)  &&  isRed(h.right)) {
			flipColors(h);
		}
		h.size = numberOfNodes(h.left) + numberOfNodes(h.right) + 1;

		return h;
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
	 * online method
	 * 
	 * is node x red; false if x is null?
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	private boolean isRed(final RedBlackNode<E> x) {
		if (x == null) {
			return false;
		}
		else {
			return x.color == red;
		}
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
	private RedBlackNode<E> leafDelete(final RedBlackNode<E> root) {
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
		RedBlackNode<E> current = this.root;
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
	public RedBlackNode<E> maxValue(RedBlackNode<E> root) {
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
	public void merge(final RedBlack<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * online method
	 * 
	 * @param root
	 * @author TelevisionNinja
	 */
	private void merge(final RedBlackNode<E> node) {
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
		RedBlackNode<E> current = this.root;
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
	public RedBlackNode<E> minValue(RedBlackNode<E> root) {
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
	private void mirror(final RedBlackNode<E> node) {
		if (node != null) {
			// do the sub-trees
			mirror(node.left);
			mirror(node.right);
			// swap the left/right pointers
			final RedBlackNode<E> temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}

	/**
	 * online method
	 * 
	 * Assuming that h is red and both h.left and h.left.left are black,
	 * make h.left or one of its children red.
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> moveRedLeft(RedBlackNode<E> h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	/**
	 * online method
	 * 
	 * Assuming that h is red and both h.right and h.right.left are black,
	 * make h.right or one of its children red
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> moveRedRight(RedBlackNode<E> h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left)) {
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
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
	 * list of nodes at the given level
	 */
	private void nodesAtGivenLevel(final RedBlackNode<E> root, final long level) {
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
	private void nodesWithinGivenRange(final RedBlackNode<E> node, final E k1, final E k2) {
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
	 * Returns the number of elements in this tree
	 * 
	 * @return the number of elements in this tree
	 */
	public int numberOfNodes() {
		return numberOfNodes(this.root);
	}

	/**
	 * online method
	 * 
	 * number of nodes in subtree rooted at x; 0 if x is null
	 * 
	 * @param x
	 * @return
	 * @author TelevisionNinja
	 */
	private int numberOfNodes(final RedBlackNode<E> x) {
		if (x == null) {
			return 0;
		}
		else {
			return x.size;
		}
	}

	/**
	 * online method
	 * 
	 * Returns the number of elements in the given range
	 *
	 * @param  lo minimum endpoint
	 * @param  hi maximum endpoint
	 * @return the number of elements between {@code lo}
	 *    (inclusive) and {@code hi} (inclusive)
	 */
	public int numberOfNodesWithiGivenRange(final E lo, final E hi) {
		if (lo.compareTo(hi) > 0) {
			return 0;
		}
		else if (search(hi)) {
			return indexOf(hi) - indexOf(lo) + 1;
		}
		else {
			return indexOf(hi) - indexOf(lo);
		}
	}

	/**
	 * textbook method
	 * 
	 * Returns a path from the root leading to the specified element
	 */
	public List<RedBlackNode<E>> path(final E e) {
		final List<RedBlackNode<E>> list = new ArrayList<>();
		RedBlackNode<E> current = this.root; // Start from the root
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
	private void postorder(final RedBlackNode<E> root) {
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
	private void preorder(final RedBlackNode<E> root) {
		if (root != null) {
			this.outputList.add(root.element);
			preorder(root.left);
			preorder(root.right);
		}
	}

	/**
	 * online method
	 * 
	 * make a right-leaning link lean to the left
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> rotateLeft(final RedBlackNode<E> h) {
		// assert (h != null) && isRed(h.right);
		final RedBlackNode<E> x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = red;
		x.size = h.size;
		h.size = numberOfNodes(h.left) + numberOfNodes(h.right) + 1;
		return x;
	}

	/**
	 * online method
	 * 
	 * make a left-leaning link lean to the right
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	private RedBlackNode<E> rotateRight(final RedBlackNode<E> h) {
		// assert (h != null) && isRed(h.left);
		final RedBlackNode<E> x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = red;
		x.size = h.size;
		h.size = numberOfNodes(h.left) + numberOfNodes(h.right) + 1;
		return x;
	}

	/**
	 * online method
	 * 
	 * Compares the receiver to another tree to see if they are structurally identical.
	 */
	public boolean sameTree(final RedBlack<E> other) {
		return sameTree(this.root, other.getRoot());
	}

	/**
	 * online method
	 * 
	 * Recursive helper -- recurs down two trees in parallel, checking to see if they are identical.
	 */
	private boolean sameTree(final RedBlackNode<E> a, final RedBlackNode<E> b) {
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
		RedBlackNode<E> current = this.root; // Start from the root
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