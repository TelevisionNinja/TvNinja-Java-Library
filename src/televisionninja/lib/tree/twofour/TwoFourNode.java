/**
 * 
 */
package televisionninja.lib.tree.twofour;

import java.util.ArrayList;
import java.util.List;

/**
 * Define a 2-4 tree node
 * 
 * @author TelevisionNinja
 */
public class TwoFourNode<E extends Comparable<E>> {
	// elements has maximum three values
	public List<E> elements = new ArrayList<>(3);
	// Each has maximum four childres
	public List<TwoFourNode<E>> child = new ArrayList<>(4);

	/** Create an empty Tree24 node */
	TwoFourNode() {
	}

	/** Create a Tree24 node with an initial element */
	TwoFourNode(final E o) {
		this.elements.add(o);
	}
}