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
	private List<E> queue = new ArrayList<>();
	
	public CircularQueue(int size) {
		this.size = size;
	}
	
	public void deQueue() {
		queue.remove(0);
		rear--;
	}
	
	public void enQueue(E obj) {
		if (rear + 1 <= size) {
			rear++;
			queue.add(obj);
		}
	}
	
	public E getFront() {
		return queue.get(0);
	}
	
	public E getRear() {
		return queue.get(rear - 1);
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		return queue.toString();
	}
}