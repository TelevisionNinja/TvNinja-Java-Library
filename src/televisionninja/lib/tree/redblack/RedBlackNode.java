/**
 * 
 */
package televisionninja.lib.tree.redblack;

/**
 * @author TelevisionNinja
 *
 */
public class RedBlackNode<E extends Comparable<E>> {
	public E element;         // associated data
	public RedBlackNode<E> left, right;  // links to left and right subtrees
	public boolean color = true;     // color of parent link
	public int size = 1;          // subtree count

	/**
	 * 
	 * @param element
	 */
	public RedBlackNode(final E element) {
		this.element = element;
	}

	/**
	 * 
	 * @param element
	 * @param color
	 * @param size
	 */
	public RedBlackNode(final E element, final boolean color, final int size) {
		this.element = element;
		this.color = color;
		this.size = size;
	}
}