/**
 * 
 */
package televisionninja.lib.tree.avl;

/**
 * textbook method
 * 
 * AVLTreeNode is TreeNode plus height
 */
public class AVLNode<E extends Comparable<E>> {
	protected int height = 0; // New data field
	public E element;
	public AVLNode<E> left,
	right;

	/**
	 * 
	 * @param e
	 */
	public AVLNode(final E e) {
		this.element = e;
	}
}