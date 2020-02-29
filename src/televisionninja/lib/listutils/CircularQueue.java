package televisionninja.lib.listutils;

import java.util.ArrayList;
import java.util.List;

/**
 * uses an arraylist
 * 
 * @author TelevisionNinja
 * 
 */
public class CircularQueue<E> {
	private int size = 0,
			rear = 0;
	private final List<E> queue = new ArrayList<>();

	public CircularQueue(final int size) {
		this.size = size;
	}

	public void deQueue() {
		this.queue.remove(0);
		this.rear--;
	}

	public void enQueue(final E obj) {
		if (this.rear + 1 <= this.size) {
			this.rear++;
			this.queue.add(obj);
		}
	}

	public E getFront() {
		return this.queue.get(0);
	}

	public E getRear() {
		return this.queue.get(this.rear - 1);
	}

	public void setSize(final int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return this.queue.toString();
	}
}