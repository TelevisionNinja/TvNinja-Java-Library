/**
 * 
 */
package televisionninja.lib.tree.bst;

/**
 * textbook method
 * 
 * This inner class is static, because it does not access any instance members defined in its outer class
 */
public class BSTNode<E extends Comparable<E>> {
	public E element;
	public BSTNode<E> left,
	right;

	/**
	 * 
	 * @param e
	 */
	public BSTNode(final E e) {
		this.element = e;
	}
}