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
public class RedBlack2<E extends Comparable<E>> {
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
			inorder(RedBlack2.this.root);
		}

		/**
		 * textbook method
		 * 
		 * Inorder traversal from a subtree
		 */
		private void inorder(final RedBlackNode2<E> root) {
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

	private RedBlackNode2<E> root;     // root of the BST

	private final List<E> outputList = new ArrayList<>();

	private long size = 0;

	/**
	 * textbook method
	 * 
	 * Create a default RB tree
	 */
	public RedBlack2() {
	}

	/**
	 * Create an RB tree from an array of elements
	 */
	public RedBlack2(final E[] objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * 
	 * @param objects
	 */
	public RedBlack2(final List<E> objects) {
		for (final E object : objects) {
			insert(object);
		}
	}

	/**
	 * creates copy of existing tree
	 * 
	 * @param tree
	 */
	public RedBlack2(final RedBlack2<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * online method
	 * 
	 * Given a binary tree. Return its nodes in level order using array for implementing queue
	 */
	public List<E> breadthFirstOrder() {
		this.outputList.clear();
		final Queue<RedBlackNode2<E>> queue = new LinkedList<>();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			/* poll() removes the present head. */
			final RedBlackNode2<E> tempNode = queue.poll();

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
	 * Connect newParent with grandParent
	 */
	private void connectNewParent(final RedBlackNode2<E> grandparent, final RedBlackNode2<E> parent, final RedBlackNode2<E> newParent) {
		if (parent == this.root) {
			this.root = newParent;
			if (this.root != null) {
				newParent.setBlack();
			}
		}
		else if (grandparent.left == parent) {
			grandparent.left = newParent;
		} else {
			grandparent.right = newParent;
		}
	}

	/**
	 * textbook method
	 * 
	 * Delete an element from the RBTree.
	 * Return true if the element is deleted successfully
	 * Return false if the element is not in the tree
	 */
	public boolean delete(final E e) {
		// Locate the node to be deleted
		RedBlackNode2<E> current = this.root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			}
			else if (e.compareTo(current.element) > 0) {
				current = current.right;
			}
			else {
				break; // Element is in the tree pointed by current
			}
		}

		if (current == null)
		{
			return false; // Element is not in the tree
		}

		List<RedBlackNode2<E>> path;

		// current node is an internal node
		if (current.left != null && current.right != null) {
			// Locate the rightmost node in the left subtree of current
			RedBlackNode2<E> rightMost = current.left;
			while (rightMost.right != null) {
				rightMost = rightMost.right; // Keep going to the right
			}

			path = path(rightMost.element); // Get path before replacement

			// Replace the element in current by the element in rightMost
			current.element = rightMost.element;
		}
		else {
			path = path(e); // Get path to current node
		}

		// Delete the last node in the path and propagate if needed
		deleteLastNodeInPath(path);

		this.size--; // After one element deleted
		return true; // Element deleted
	}

	/**
	 * textbook method
	 * 
	 * Delete the last node from the path.
	 */
	public void deleteLastNodeInPath(final List<RedBlackNode2<E>> path) {
		final int i = path.size() - 1; // Index to the node in the path
		// u is the last node in the path
		final RedBlackNode2<E> u = (path.get(i)),
				parentOfu = (u == this.root) ? null : path.get(i - 1),
						grandparentOfu = (parentOfu == null || parentOfu == this.root) ? null : path.get(i - 2),
								childOfu = (u.left == null) ? u.right : u.left;

		// Delete node u. Connect childOfu with parentOfu
		connectNewParent(parentOfu, u, childOfu);

		// Recolor the nodes and fix double black if needed
		if (childOfu == this.root || u.isRed()) {
			return; // Done if childOfu is root or if u is red
		} else if (childOfu != null && childOfu.isRed()) {
			childOfu.setBlack(); // Set it black, done
		} else {
			// Fix double black on parentOfu
			fixDoubleBlack(grandparentOfu, parentOfu, childOfu, path, i);
		}
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
	private long distanceBetweenNodes(final RedBlackNode2<E> root, final E a, final E b) {
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
	private long distanceFromRoot(final RedBlackNode2<E> root, final E x) {
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
	private void doubleTree(final RedBlackNode2<E> node) {
		RedBlackNode2<E> oldLeft;
		if (node != null) {
			// do the subtrees
			doubleTree(node.left);
			doubleTree(node.right);
			// duplicate this node to its left
			oldLeft = node.left;
			node.left = new RedBlackNode2<>(node.element);
			node.left.left = oldLeft;
		}
	}

	/**
	 * textbook method
	 * 
	 * Ensure that the tree is a red-black tree
	 */
	private void ensureRBTree(final E e) {
		// Get the path that leads to element e from the root
		final List<RedBlackNode2<E>> path = path(e);

		final int i = path.size() - 1; // Index to the current node in the path

		final RedBlackNode2<E> u = (path.get(i)), // u is the last node in the path. u contains element e
				v = (u == this.root) ? null : path.get(i - 1); // v is the parent of of u, if exists

		u.setRed(); // It is OK to set u red

		if (u == this.root) {
			u.setBlack();
		} else if (v.isRed())
		{
			fixDoubleRed(u, v, path, i); // Fix double red violation at u
		}
	}

	/**
	 * textbook method
	 * 
	 * Fix the double black problem at node parent
	 */
	private void fixDoubleBlack(RedBlackNode2<E> grandparent, RedBlackNode2<E> parent, RedBlackNode2<E> db, final List<RedBlackNode2<E>> path, final int i) {
		// Obtain y, y1, and y2
		final RedBlackNode2<E> y = (parent.right == db) ? parent.left : parent.right,
				y1 = y.left,
				y2 = y.right;

		if (y.isBlack() && y1 != null && y1.isRed()) {
			if (parent.right == db) {
				// Case 1.1: y is a left black sibling and y1 is red
				connectNewParent(grandparent, parent, y);
				recolor(parent, y, y1); // Adjust colors

				// Adjust child links
				parent.left = y.right;
				y.right = parent;
			}
			else {
				// Case 1.3: y is a right black sibling and y1 is red
				connectNewParent(grandparent, parent, y1);
				recolor(parent, y1, y); // Adjust colors

				// Adjust child links
				parent.right = y1.left;
				y.left = y1.right;
				y1.left = parent;
				y1.right = y;
			}
		}
		else if (y.isBlack() && y2 != null && y2.isRed()) {
			if (parent.right == db) {
				// Case 1.2: y is a left black sibling and y2 is red
				connectNewParent(grandparent, parent, y2);
				recolor(parent, y2, y); // Adjust colors

				// Adjust child links
				y.right = y2.left;
				parent.left = y2.right;
				y2.left = y;
				y2.right = parent;
			}
			else {
				// Case 1.4: y is a right black sibling and y2 is red
				connectNewParent(grandparent, parent, y);
				recolor(parent, y, y2); // Adjust colors

				// Adjust child links
				y.left = parent;
				parent.right = y1;
			}
		}
		else if (y.isBlack()) {
			// Case 2: y is black and y's children are black or null
			y.setRed(); // Change y to red
			if (parent.isRed()) {
				parent.setBlack(); // Done
			} else if (parent != this.root) {
				// Propagate double black to the parent node
				// Fix new appearance of double black recursively
				db = parent;
				parent = grandparent;
				grandparent =
						(i >= 3) ? path.get(i - 3) : null;
						fixDoubleBlack(grandparent, parent, db, path, i - 1);
			}
		}
		else { // y.isRed()
			if (parent.right == db) {
				// Case 3.1: y is a left red child of parent
				parent.left = y2;
				y.right = parent;
			}
			else {
				// Case 3.2: y is a right red child of parent
				parent.right = y.left;
				y.left = parent;
			}

			parent.setRed(); // Color parent red
			y.setBlack(); // Color y black
			connectNewParent(grandparent, parent, y); // y is new parent
			fixDoubleBlack(y, parent, db, path, i - 1);
		}
	}

	/**
	 * textbook method
	 * 
	 * Fix double red violation at node u
	 */
	private void fixDoubleRed(RedBlackNode2<E> u, RedBlackNode2<E> v, final List<RedBlackNode2<E>> path, final int i) {
		// w is the grandparent of u
		final RedBlackNode2<E> w = (path.get(i - 2)),
				parentOfw = (w == this.root) ? null : path.get(i - 3),
						x = (w.left == v) ? w.right : w.left;// Get v's sibling named x

		if (x == null || x.isBlack()) {
			// Case 1: v's sibling x is black
			if (w.left == v && v.left == u) {
				// Case 1.1: u < v < w, Restructure and recolor nodes
				restructureRecolor(u, v, w, w, parentOfw);

				w.left = v.right; // v.right is y3 in Figure 11.7
				v.right = w;
			}
			else if (w.left == v && v.right == u) {
				// Case 1.2: v < u < w, Restructure and recolor nodes
				restructureRecolor(v, u, w, w, parentOfw);
				v.right = u.left;
				w.left = u.right;
				u.left = v;
				u.right = w;
			}
			else if (w.right == v && v.right == u) {
				// Case 1.3: w < v < u, Restructure and recolor nodes
				restructureRecolor(w, v, u, w, parentOfw);
				w.right = v.left;
				v.left = w;
			}
			else {
				// Case 1.4: w < u < v, Restructure and recolor nodes
				restructureRecolor(w, u, v, w, parentOfw);
				w.right = u.left;
				v.left = u.right;
				u.left = w;
				u.right = v;
			}
		}
		else { // Case 2: v's sibling x is red
			// Recolor nodes
			w.setRed();
			u.setRed();
			w.left.setBlack();
			w.right.setBlack();

			if (w == this.root) {
				w.setBlack();
			}
			else if (parentOfw.isRed()) {
				// Propagate along the path to fix new double red violation
				u = w;
				v = parentOfw;
				fixDoubleRed(u, v, path, i - 2); // i – 2 propagates upward
			}
		}
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public RedBlackNode2<E> getRoot() {
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
	public boolean hasPathSumByte(final RedBlackNode2<Byte> node, final long sum) {
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
	public boolean hasPathSumCharacter(final RedBlackNode2<Character> node, final long sum) {
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
	public boolean hasPathSumDouble(final RedBlackNode2<Double> node, final double sum) {
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
	public boolean hasPathSumFloat(final RedBlackNode2<Float> node, final float sum) {
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
	public boolean hasPathSumInteger(final RedBlackNode2<Integer> node, final long sum) {
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
	public boolean hasPathSumLong(final RedBlackNode2<Long> node, final long sum) {
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
	public boolean hasPathSumShort(final RedBlackNode2<Short> node, final long sum) {
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
	private long height(final RedBlackNode2<E> root) {
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
	private void inorder(final RedBlackNode2<E> root) {
		if (root != null) {
			inorder(root.left);
			this.outputList.add(root.element);
			inorder(root.right);
		}
	}

	/**
	 * textbook method
	 * 
	 * Override the insert method to balance the tree if necessary
	 */
	public boolean insert(final E e) {
		final boolean successful = insertIntoTree(e);
		if (!successful) {
			return false; // e is already in the tree
		} else {
			ensureRBTree(e);
		}

		return true; // e is inserted
	}

	/**
	 * textbook method
	 * 
	 * Insert element e into the binary search tree.
	 * Return true if the element is inserted successfully.
	 */
	private boolean insertIntoTree(final E e) {
		if (this.root == null) {
			this.root = new RedBlackNode2<>(e); // Create a new root
		}
		else {
			// Locate the parent node
			RedBlackNode2<E> parent = null,
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
				parent.left = new RedBlackNode2<>(e);
			}
			else {
				parent.right = new RedBlackNode2<>(e);
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
	private RedBlackNode2<E> leafDelete(final RedBlackNode2<E> root) {
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
		RedBlackNode2<E> current = this.root;
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
	public RedBlackNode2<E> maxValue(RedBlackNode2<E> root) {
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
	public void merge(final RedBlack2<E> tree) {
		merge(tree.getRoot());
	}

	/**
	 * online method
	 * 
	 * @param root
	 * @author TelevisionNinja
	 */
	private void merge(final RedBlackNode2<E> node) {
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
		RedBlackNode2<E> current = this.root;
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
	public RedBlackNode2<E> minValue(RedBlackNode2<E> root) {
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
	private void mirror(final RedBlackNode2<E> node) {
		if (node != null) {
			// do the sub-trees
			mirror(node.left);
			mirror(node.right);
			// swap the left/right pointers
			final RedBlackNode2<E> temp = node.left;
			node.left = node.right;
			node.right = temp;
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
	 * list of nodes at the given level
	 */
	private void nodesAtGivenLevel(final RedBlackNode2<E> root, final long level) {
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
	private void nodesWithinGivenRange(final RedBlackNode2<E> node, final E k1, final E k2) {
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
	 * textbook method
	 * 
	 * Returns a path from the root leading to the specified element
	 */
	public List<RedBlackNode2<E>> path(final E e) {
		final List<RedBlackNode2<E>> list = new ArrayList<>();
		RedBlackNode2<E> current = this.root; // Start from the root
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
	private void postorder(final RedBlackNode2<E> root) {
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
	private void preorder(final RedBlackNode2<E> root) {
		if (root != null) {
			this.outputList.add(root.element);
			preorder(root.left);
			preorder(root.right);
		}
	}

	/**
	 * textbook method
	 * 
	 * Recolor parent, newParent, and c. Case 1 removal
	 */
	private void recolor(final RedBlackNode2<E> parent, final RedBlackNode2<E> newParent, final RedBlackNode2<E> c) {
		// Retain the parent's color for newParent
		if (parent.isRed()) {
			newParent.setRed();
		} else {
			newParent.setBlack();
		}

		// c and parent become the children of newParent, set them black
		parent.setBlack();
		c.setBlack();
	}

	/**
	 * textbook method
	 * 
	 * Connect b with parentOfw and recolor a, b, c for a < b < c
	 */
	private void restructureRecolor(final RedBlackNode2<E> a, final RedBlackNode2<E> b, final RedBlackNode2<E> c, final RedBlackNode2<E> w, final RedBlackNode2<E> parentOfw) {
		if (parentOfw == null) {
			this.root = b;
		} else if (parentOfw.left == w) {
			parentOfw.left = b;
		} else {
			parentOfw.right = b;
		}

		b.setBlack(); // b becomes the root in the subtree
		a.setRed(); // a becomes the left child of b
		c.setRed(); // c becomes the right child of b
	}

	/**
	 * online method
	 * 
	 * Compares the receiver to another tree to see if they are structurally identical.
	 */
	public boolean sameTree(final RedBlack2<E> other) {
		return sameTree(this.root, other.getRoot());
	}

	/**
	 * online method
	 * 
	 * Recursive helper -- recurs down two trees in parallel, checking to see if they are identical.
	 */
	private boolean sameTree(final RedBlackNode2<E> a, final RedBlackNode2<E> b) {
		// 1. both empty -> true
		if (a == null && b == null) {
			return true;
		}
		// 2. both non-empty -> compare them
		else if (a != null && b != null) {
			return a.element.equals(b.element) && sameTree(a.left, b.left) && sameTree(a.right, b.right);
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
		RedBlackNode2<E> current = this.root; // Start from the root
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