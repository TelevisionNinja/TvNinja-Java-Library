/**
 * 
 */
package televisionninja.lib.tree.redblack;

/**
 * RBTreeNode is TreeNode plus color indicator
 * 
 * @author TelevisionNinja
 */
public class RedBlackNode2<E extends Comparable<E>> {
	private boolean red = true; // Indicate node color

	public E element;

	public RedBlackNode2<E> left,
	right;

	public int blackHeight;

	public RedBlackNode2(final E e) {
		this.element = e;
	}

	public boolean isBlack() {
		return !this.red;
	}

	public boolean isRed() {
		return this.red;
	}

	public void setBlack() {
		this.red = false;
	}

	public void setRed() {
		this.red = true;
	}
}