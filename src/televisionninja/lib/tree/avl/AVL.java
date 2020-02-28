/**
 * 
 */
package televisionninja.lib.tree.avl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author TelevisionNinja
 *
 */
public class AVL<E extends Comparable<E>> {
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
			inorder(AVL.this.root);
		}

		/**
		 * textbook method
		 * 
		 * Inorder traversal from a subtree
		 */
		private void inorder(final AVLNode<E> root) {
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

	private AVLNode<E> root;     // root of the BST

	private final List<E> outputList = new ArrayList<>();

	private long size = 0;

	/**
	 * Initializes an empty tree.
	 */
	public AVL() {
	}

	/**
	 * creates copy of existing tree
	 * 
	 * @param tree
	 */
	public AVL(final AVL<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * textbook method
	 * 
	 * Create a binary search tree from an array of objects
	 */
	public AVL(final E[] objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * 
	 * @param objects
	 */
	public AVL(final List<E> objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * textbook method
	 * 
	 * Return the balance factor of the node
	 */
	private int balanceFactor(final AVLNode<E> node) {
		if (node.right == null) {
			return -node.height;
		}
		else if (node.left == null) {
			return node.height;
		}
		else {
			return node.right.height - node.left.height;
		}
	}

	/**
	 * textbook method
	 * 
	 * Balance LL (see Figure 26.2)
	 */
	private void balanceLL(final AVLNode<E> A, final AVLNode<E> parentOfA) {
		final AVLNode<E> B = A.left; // A is left-heavy and B is left-heavy

		if (A == this.root) {
			this.root = B;
		}
		else {
			if (parentOfA.left == A) {
				parentOfA.left = B;
			}
			else {
				parentOfA.right = B;
			}
		}

		A.left = B.right; // Make T2 the left subtree of A
		B.right = A; // Make A the left child of B
		updateHeight(A);
		updateHeight(B);
	}

	/**
	 * textbook method
	 * 
	 * Balance LR (see Figure 26.4)
	 */
	private void balanceLR(final AVLNode<E> A, final AVLNode<E> parentOfA) {
		/*
		 * A is left-heavy
		 * B is right-heavy
		 */
		final AVLNode<E> B = A.left,
				C = B.right;

		if (A == this.root) {
			this.root = C;
		}
		else {
			if (parentOfA.left == A) {
				parentOfA.left = C;
			}
			else {
				parentOfA.right = C;
			}
		}

		A.left = C.right; // Make T3 the left subtree of A
		B.right = C.left; // Make T2 the right subtree of B
		C.left = B;
		C.right = A;

		// Adjust heights
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
	}

	/**
	 * textbook method
	 * 
	 * Balance the nodes in the path from the specified
	 * node to the root if necessary
	 */
	private void balancePath(final E e) {
		final List<AVLNode<E>> path = path(e);
		for (int i = path.size() - 1; i >= 0; i--) {
			final AVLNode<E> A = path.get(i);
			updateHeight(A);
			final AVLNode<E> parentOfA = (A == this.root) ? null :
				path.get(i - 1);
			switch (balanceFactor(A)) {
			case -2:
				if (balanceFactor(A.left) <= 0) {
					balanceLL(A, parentOfA); // Perform LL rotation
				}
				else {
					balanceLR(A, parentOfA); // Perform LR rotation
				}
				break;
			case 2:
				if (balanceFactor(A.right) >= 0) {
					balanceRR(A, parentOfA); // Perform RR rotation
				}
				else {
					balanceRL(A, parentOfA); // Perform RL rotation
				}
			default:
			}
		}
	}

	/**
	 * textbook method
	 * 
	 * Balance RL (see Figure 26.5)
	 */
	private void balanceRL(final AVLNode<E> A, final AVLNode<E> parentOfA) {
		/*
		 * A is right-heavy
		 * B is left-heavy
		 */
		final AVLNode<E> B = A.right,
				C = B.left;
		if (A == this.root) {
			this.root = C;
		}
		else {
			if (parentOfA.left == A) {
				parentOfA.left = C;
			}
			else {
				parentOfA.right = C;
			}
		}

		A.right = C.left; // Make T2 the right subtree of A
		B.left = C.right; // Make T3 the left subtree of B
		C.left = A;
		C.right = B;

		// Adjust heights
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
	}

	/**
	 * textbook method
	 * 
	 * Balance RR (see Figure 26.3)
	 */
	private void balanceRR(final AVLNode<E> A, final AVLNode<E> parentOfA) {
		final AVLNode<E> B = A.right; // A is right-heavy and B is right-heavy
		if (A == this.root) {
			this.root = B;
		}
		else {
			if (parentOfA.left == A) {
				parentOfA.left = B;
			}
			else {
				parentOfA.right = B;
			}
		}

		A.right = B.left; // Make T2 the right subtree of A
		B.left = A;
		updateHeight(A);
		updateHeight(B);
	}

	/**
	 * online method
	 * 
	 * Given a binary tree. Return its nodes in level order using array for implementing queue
	 */
	public List<E> breadthFirstOrder() {
		this.outputList.clear();
		final Queue<AVLNode<E>> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			/* poll() removes the present head. */
			final AVLNode<E> tempNode = queue.poll();

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
	 * Delete an element from the AVL tree.
	 * Return true if the element is deleted successfully
	 * Return false if the element is not in the tree
	 */
	public boolean delete(final E element) {
		if (this.root == null) {
			return false; // Element is not in the tree
		}
		// Locate the node to be deleted and also locate its parent node
		AVLNode<E> parent = null,
				current = this.root;
		while (current != null) {
			if (element.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			}
			else if (element.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			}
			else {
				break; // Element is in the tree pointed by current
			}
		}

		if (current == null) {
			return false; // Element is not in the tree
		}

		// Case 1: current has no left children (See Figure 25.10)
		if (current.left == null) {
			// Connect the parent with the right child of the current node
			if (parent == null) {
				this.root = current.right;
			}
			else {
				if (element.compareTo(parent.element) < 0) {
					parent.left = current.right;
				}
				else {
					parent.right = current.right;
				}

				// Balance the tree if necessary
				balancePath(parent.element);
			}
		}
		else {

			// Case 2: The current node has a left child
			// Locate the rightmost node in the left subtree of
			// the current node and also its parent
			AVLNode<E> parentOfRightMost = current,
					rightMost = current.left;
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right; // Keep going to the right
			}
			//Replace the element in current by the element in rightMost
			current.element = rightMost.element;

			// Eliminate rightmost node
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			}
			else {
				// Special case: parentOfRightMost is current
				parentOfRightMost.left = rightMost.left;
			}

			// Balance the tree if necessary
			balancePath(parentOfRightMost.element);
		}

		this.size--;
		return true; // Element inserted
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
	private long distanceBetweenNodes(final AVLNode<E> root, final E a, final E b) {
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
	private long distanceFromRoot(final AVLNode<E> root, final E x) {
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
	private void doubleTree(final AVLNode<E> node) {
		AVLNode<E> oldLeft;
		if (node != null) {
			// do the subtrees
			doubleTree(node.left);
			doubleTree(node.right);
			// duplicate this node to its left
			oldLeft = node.left;
			node.left = new AVLNode<>(node.element);
			node.left.left = oldLeft;
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
	private AVLNode<E> get(final AVLNode<E> x, final int index) {
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
	 * online method
	 * 
	 * Return the element in the tree whose rank is {@code k}.
	 * This is the (k + 1)st smallest key in the tree.
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

	public AVLNode<E> getRoot() {
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
	public boolean hasPathSumByte(final AVLNode<Byte> node, final long sum) {
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
	public boolean hasPathSumCharacter(final AVLNode<Character> node, final long sum) {
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
	public boolean hasPathSumDouble(final AVLNode<Double> node, final double sum) {
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
	public boolean hasPathSumFloat(final AVLNode<Float> node, final float sum) {
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
	public boolean hasPathSumInteger(final AVLNode<Integer> node, final long sum) {
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
	public boolean hasPathSumLong(final AVLNode<Long> node, final long sum) {
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
	public boolean hasPathSumShort(final AVLNode<Short> node, final long sum) {
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
	private long height(final AVLNode<E> root) {
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
	private int indexOf(final E element, final AVLNode<E> x) {
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
	private void inorder(final AVLNode<E> root) {
		if (root != null) {
			inorder(root.left);
			this.outputList.add(root.element);
			inorder(root.right);
		}
	}

	/**
	 * textbook method
	 * 
	 * Insert an element and rebalance if necessary
	 */
	public boolean insert(final E e) {
		if (!insertElement(e)) {
			return false; // e is already in the tree
		}
		else {
			balancePath(e); // Balance from e to the root if necessary
		}

		return true; // e is inserted
	}

	/**
	 * textbook method
	 * 
	 * Insert element e into the binary search tree.
	 * Return true if the element is inserted successfully.
	 */
	private boolean insertElement(final E e) {
		if (this.root == null) {
			this.root = new AVLNode<>(e); // Create a new root
		}
		else {
			// Locate the parent node
			AVLNode<E> parent = null,
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
				parent.left = new AVLNode<>(e);
			}
			else {
				parent.right = new AVLNode<>(e);
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
	private AVLNode<E> leafDelete(final AVLNode<E> root) {
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
		AVLNode<E> current = this.root;
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
	public AVLNode<E> maxValue(AVLNode<E> root) {
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
	public void merge(final AVL<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * online method
	 * 
	 * @param root
	 * @author TelevisionNinja
	 */
	private void merge(final AVLNode<E> node) {
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
		AVLNode<E> current = this.root;
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
	public AVLNode<E> minValue(AVLNode<E> root) {
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
	private void mirror(final AVLNode<E> node) {
		if (node != null) {
			// do the sub-trees
			mirror(node.left);
			mirror(node.right);
			// swap the left/right pointers
			final AVLNode<E> temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}

	/**
	 * online method
	 * 
	 * list of nodes at the given level
	 */
	private void nodesAtGivenLevel(final AVLNode<E> root, final long level) {
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
	private void nodesWithinGivenRange(final AVLNode<E> node, final E k1, final E k2) {
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
	private int numberOfNodes(final AVLNode<E> x) {
		if (x == null) {
			return 0;
		}
		else {
			return x.height;
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
	public List<AVLNode<E>> path(final E e) {
		final List<AVLNode<E>> list = new ArrayList<>();
		AVLNode<E> current = this.root; // Start from the root
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
	private void postorder(final AVLNode<E> root) {
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
	private void preorder(final AVLNode<E> root) {
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
	public boolean sameTree(final AVL<E> other) {
		return sameTree(this.root, other.getRoot());
	}

	/**
	 * online method
	 * 
	 * Recursive helper -- recurs down two trees in parallel, checking to see if they are identical.
	 */
	private boolean sameTree(final AVLNode<E> a, final AVLNode<E> b) {
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
		AVLNode<E> current = this.root; // Start from the root
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

	/**
	 * textbook method
	 * 
	 * Update the height of a specified node
	 */
	private void updateHeight(final AVLNode<E> node) {
		if (node.left == null && node.right == null) {
			node.height = 0;
		}
		else if (node.left == null) {
			node.height = 1 + node.right.height;
		}
		else if (node.right == null) {
			node.height = 1 + node.left.height;
		}
		else {
			node.height = 1 + Math.max(node.right.height, node.left.height);
		}
	}
}