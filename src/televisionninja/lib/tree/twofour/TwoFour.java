/**
 * 
 */
package televisionninja.lib.tree.twofour;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TelevisionNinja
 *
 */
public class TwoFour<E extends Comparable<E>> {
	private TwoFourNode<E> root;
	private int size;

	/**
	 * textbook method
	 * 
	 * Create a default 2-4 tree
	 */
	public TwoFour() {
	}

	/**
	 * textbook method
	 * 
	 * Create a 2-4 tree from an array of objects
	 */
	public TwoFour(final E[] elements) {
		for (final E element : elements) {
			insert(element);
		}
	}

	/**
	 * textbook method
	 * 
	 * Remove all elements from the tree
	 */
	public void clear() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * textbook method
	 * 
	 * Delete the specified element from the tree
	 */
	public boolean delete(final E e) {
		// Locate the node that contains the element e
		TwoFourNode<E> node = this.root;
		while (node != null) {
			if (matched(e, node)) {
				delete(e, node); // Delete element e from node
				this.size--; // After one element deleted
				return true; // Element deleted successfully
			}
			else {
				node = getChildNode(e, node);
			}
		}

		return false; // Element not in the tree
	}

	/**
	 * textbook method
	 * 
	 * Delete the specified element from the node
	 */
	private void delete(final E e, final TwoFourNode<E> node) {
		if (node.child.size() == 0) { // e is in a leaf node
			// Get the path that leads to e from the root
			final List<TwoFourNode<E>> path = path(e);

			node.elements.remove(e); // Remove element e

			if (node == this.root) { // Special case
				if (node.elements.size() == 0) {
					this.root = null; // Empty tree
				}
				return; // Done
			}

			validate(e, node, path); // Check underflow node
		}
		else { // e is in an internal node
			// Locate the rightmost node in the left subtree of the node
			final int index = locate(e, node); // Index of e in node
			TwoFourNode<E> current = node.child.get(index);
			while (current.child.size() > 0) {
				current = current.child.get(current.child.size() - 1);
			}
			final E rightmostElement = current.elements.get(current.elements.size() - 1);

			// Get the path that leads to e from the root
			final List<TwoFourNode<E>> path = path(rightmostElement);

			// Replace the deleted element with the rightmost element
			node.elements.set(index, current.elements.remove(current.elements.size() - 1));

			validate(rightmostElement, current, path); // Check underflow
		}
	}

	/**
	 * textbook method
	 * 
	 * Locate a child node to search element e
	 */
	private TwoFourNode<E> getChildNode(final E e, final TwoFourNode<E> node) {
		if (node.child.size() == 0) {
			return null; // node is a leaf
		}

		final int i = locate(e, node); // Locate the insertion point for e
		return node.child.get(i); // Return the child node
	}

	/**
	 * textbook method
	 * 
	 * Get the number of nodes in the tree
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * textbook method
	 * 
	 * Inorder traversal from the root
	 */
	public void inorder() {//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		// Left as exercise
	}

	/**
	 * textbook method
	 * 
	 * Insert element e into the tree
	 * Return true if the element is inserted successfully
	 */
	public boolean insert(final E e) {
		if (this.root == null) {
			this.root = new TwoFourNode<>(e); // Create a new root for element
		}
		else {
			// Locate the leaf node for inserting e
			TwoFourNode<E> leafNode = null, current = this.root;
			while (current != null) {
				if (matched(e, current)) {
					return false; // Duplicate element found, nothing inserted
				}
				else {
					leafNode = current;
					current = getChildNode(e, current);
				}
			}

			// Insert the element e into the leaf node
			insert(e, null, leafNode); // The right child of e is null
		}

		this.size++; // Increase size
		return true; // Element inserted
	}

	/**
	 * textbook method
	 * 
	 * Insert element e into node u
	 */
	private void insert(E e, TwoFourNode<E> rightChildOfe, TwoFourNode<E> u) {
		// Get the search path that leads to element e
		final List<TwoFourNode<E>> path = path(e);

		for (int i = path.size() - 1; i >= 0; i--) {
			if (u.elements.size() < 3) { // u is a 2-node or 3-node
				insert23(e, rightChildOfe, u); // Insert e to node u
				break; // No further insertion to u's parent needed
			}
			else {
				final TwoFourNode<E> v = new TwoFourNode<>(); // Create a new node
				final E median = split(e, rightChildOfe, u, v); // Split u

				if (u == this.root) {
					this.root = new TwoFourNode<>(median); // New root
					this.root.child.add(u); // u is the left child of median
					this.root.child.add(v); // v is the right child of median
					break; // No further insertion to u's parent needed
				}
				else {
					// Use new values for the next iteration in the for loop
					e = median; // Element to be inserted to parent
					rightChildOfe = v; // Right child of the element
					u = path.get(i - 1); // New node to insert element
				}
			}
		}
	}

	/**
	 * textbook method
	 * 
	 * Insert element to a 2- or 3- and return the insertion point
	 */
	private void insert23(final E e, final TwoFourNode<E> rightChildOfe, final TwoFourNode<E> node) {
		final int i = this.locate(e, node); // Locate where to insert
		node.elements.add(i, e); // Insert the element into the node
		if (rightChildOfe != null) {
			node.child.add(i + 1, rightChildOfe); // Insert the child link
		}
	}

	/**
	 * textbook method
	 * 
	 * Return true if the tree is empty
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * textbook method
	 * 
	 * Return an iterator to traverse elements in the tree
	 */
	public java.util.Iterator<E> iterator() {//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		// Left as exercise
		return null;
	}

	/**
	 * textbook method
	 * 
	 * Perform a fusion with a left sibling
	 */
	private void leftSiblingFusion(final int k, final TwoFourNode<E> leftNode, final TwoFourNode<E> u, final TwoFourNode<E> parentOfu) {
		// Transfer an element from the parent to the left sibling
		leftNode.elements.add(parentOfu.elements.remove(k - 1));

		// Remove the link to the empty node
		parentOfu.child.remove(k);

		// Adjust child links for non-leaf node
		if (u.child.size() > 0) {
			leftNode.child.add(u.child.remove(0));
		}
	}

	/**
	 * textbook method
	 * 
	 * Perform a transfer with a left sibling
	 */
	private void leftSiblingTransfer(final int k, final TwoFourNode<E> u, final TwoFourNode<E> parentOfu) {
		// Move an element from the parent to u
		u.elements.add(0, parentOfu.elements.get(k - 1));

		// Move an element from the left node to the parent
		final TwoFourNode<E> leftNode = parentOfu.child.get(k - 1);
		parentOfu.elements.set(k - 1, leftNode.elements.remove(leftNode.elements.size() - 1));

		// Move the child link from left sibling to the node
		if (leftNode.child.size() > 0) {
			u.child.add(0, leftNode.child.remove(leftNode.child.size() - 1));
		}
	}

	/**
	 * textbook method
	 * 
	 * Locate the insertion point of the element in the node
	 */
	private int locate(final E o, final TwoFourNode<E> node) {
		for (int i = 0; i < node.elements.size(); i++) {
			if (o.compareTo(node.elements.get(i)) <= 0) {
				return i;
			}
		}

		return node.elements.size();
	}

	/**
	 * textbook method
	 * 
	 * Return true if the element is found in this node
	 */
	private boolean matched(final E e, final TwoFourNode<E> node) {
		for (final E element : node.elements) {
			if (element.equals(e)) {
				return true; // Element found
			}
		}

		return false; // No match in this node
	}

	/**
	 * textbook method
	 * 
	 * Return a search path that leads to element e
	 */
	private List<TwoFourNode<E>> path(final E e) {
		final List<TwoFourNode<E>> list = new ArrayList<>();
		TwoFourNode<E> current = this.root; // Start from the root

		while (current != null) {
			list.add(current); // Add the node to the list
			if (matched(e, current)) {
				break; // Element found
			}
			else {
				current = getChildNode(e, current);
			}
		}

		return list; // Return an array of nodes
	}

	/**
	 * textbook method
	 * 
	 * Postorder traversal from the root
	 */
	public void postorder() {//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		// Left as exercise
	}

	/**
	 * textbook method
	 * 
	 * Preorder traversal from the root
	 */
	public void preorder() {//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		preorder(this.root);
	}

	/**
	 * textbook method
	 * 
	 * Preorder traversal from a subtree
	 */
	private void preorder(final TwoFourNode<E> root) {//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		if (root == null) {
			return;
		}
		for (int i = 0; i < root.elements.size(); i++) {
			System.out.print(root.elements.get(i) + " ");
		}

		for (int i = 0; i < root.child.size(); i++) {
			preorder(root.child.get(i));
		}
	}

	/**
	 * textbook method
	 * 
	 * Perform a fusion with a right sibling
	 */
	private void rightSiblingFusion(final int k, final TwoFourNode<E> rightNode, final TwoFourNode<E> u, final TwoFourNode<E> parentOfu) {
		// Transfer an element from the parent to the right sibling
		rightNode.elements.add(0, parentOfu.elements.remove(k));

		// Remove the link to the empty node
		parentOfu.child.remove(k);

		// Adjust child links for non-leaf node
		if (u.child.size() > 0) {
			rightNode.child.add(0, u.child.remove(0));
		}
	}

	/**
	 * textbook method
	 * 
	 * Perform a transfer with a right sibling
	 */
	private void rightSiblingTransfer(final int k, final TwoFourNode<E> u, final TwoFourNode<E> parentOfu) {
		// Transfer an element from the parent to u
		u.elements.add(parentOfu.elements.get(k));

		// Transfer an element from the right node to the parent
		final TwoFourNode<E> rightNode = parentOfu.child.get(k + 1);
		parentOfu.elements.set(k, rightNode.elements.remove(0));

		// Move the child link from right sibling to the node
		if (rightNode.child.size() > 0) {
			u.child.add(rightNode.child.remove(0));
		}
	}

	/**
	 * textbook method
	 * 
	 * Search an element in the tree
	 */
	public boolean search(final E e) {
		TwoFourNode<E> current = this.root; // Start from the root

		while (current != null) {
			if (matched(e, current)) { // Element is in the node
				return true; // Element found
			}
			else {
				current = getChildNode(e, current); // Search in a subtree
			}
		}

		return false; // Element is not in the tree
	}

	/**
	 * textbook method
	 * 
	 * Split a 4-node u into u and v and insert e to u or v
	 */
	private E split(final E e, final TwoFourNode<E> rightChildOfe, final TwoFourNode<E> u, final TwoFourNode<E> v) {
		// Move the last element in node u to node v
		v.elements.add(u.elements.remove(2));
		final E median = u.elements.remove(1);

		// Split children for a non-leaf node
		// Move the last two children in node u to node v
		if (u.child.size() > 0) {
			v.child.add(u.child.remove(2));
			v.child.add(u.child.remove(2));
		}

		// Insert e into a 2- or 3- node u or v.
		if (e.compareTo(median) < 0) {
			insert23(e, rightChildOfe, u);
		}
		else {
			insert23(e, rightChildOfe, v);
		}

		return median; // Return the median element
	}

	/**
	 * textbook method
	 * 
	 * Perform transfer and confusion operations if necessary
	 */
	private void validate(final E e, TwoFourNode<E> u, final List<TwoFourNode<E>> path) {
		for (int i = path.size() - 1; u.elements.size() == 0; i--) {
			final TwoFourNode<E> parentOfu = path.get(i - 1); // Get parent of u
			final int k = locate(e, parentOfu); // Index of e in the parent node

			// Check two siblings
			if (k > 0 && parentOfu.child.get(k - 1).elements.size() > 1) {
				leftSiblingTransfer(k, u, parentOfu);
			}
			else if (k + 1 < parentOfu.child.size() && parentOfu.child.get(k + 1).elements.size() > 1) {
				rightSiblingTransfer(k, u, parentOfu);
			}
			else if (k - 1 >= 0) { // Fusion with a left sibling
				// Get left sibling of node u
				final TwoFourNode<E> leftNode = parentOfu.child.get(k - 1);

				// Perform a fusion with left sibling on node u
				leftSiblingFusion(k, leftNode, u, parentOfu);

				// Done when root becomes empty
				if (parentOfu == this.root && parentOfu.elements.size() == 0) {
					this.root = leftNode;
					break;
				}

				u = parentOfu; // Back to the loop to check the parent node
			}
			else { // Fusion with right sibling (right sibling must exist)
				// Get left sibling of node u
				final TwoFourNode<E> rightNode = parentOfu.child.get(k + 1);

				// Perform a fusion with right sibling on node u
				rightSiblingFusion(k, rightNode, u, parentOfu);

				// Done when root becomes empty
				if (parentOfu == this.root && parentOfu.elements.size() == 0) {
					this.root = rightNode;
					break;
				}

				u = parentOfu; // Back to the loop to check the parent node
			}
		}
	}
}