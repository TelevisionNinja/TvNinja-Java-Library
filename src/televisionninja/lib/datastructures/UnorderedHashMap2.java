/**
 * 
 */
package televisionninja.lib.datastructures;

import televisionninja.lib.hashes.Murmur;

/**
 * @author TelevisionNinja
 *
 */
public class UnorderedHashMap2<K, V> {
	private class Entry<eK, eV> {
		public eK key;
		public eV value;

		Entry(final eK key, final eV value) {
			this.key = key;
			this.value = value;
		}
	}

	private int size = 0;
	private Entry<K, V>[] entries;
	private int seed = 0;
	private UnorderedHashMap2<K, V> collisionHashTable;
	private int startingSize;

	@SuppressWarnings("unchecked")
	UnorderedHashMap2() {
		this.startingSize = (int) (Math.pow(2, 4));
		this.entries = new Entry[this.startingSize];
	}

	/**
	 * 
	 * @param {Number} startingSize default of 16
	 */
	@SuppressWarnings("unchecked")
	UnorderedHashMap2(final int startingSize) {
		if (startingSize <= 0) {
			this.startingSize = (int) (Math.pow(2, 4));
		}
		else {
			this.startingSize = startingSize;
		}

		this.entries = new Entry[this.startingSize];
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		this.size = 0;
		this.entries = new Entry[this.startingSize];
		this.collisionHashTable = null;
	}

	public void delete(final K key) {
		final int index = this.hash(key);
		final Entry<K, V> entry = this.entries[index];

		if (entry == null) {
			return;
		}

		if (key.equals(entry.key)) { // key == entry.key | the entry is in this hash map
			if (this.collisionHashTable == null) {
				this.entries[index] = null; // delete this.entries[index]
			}
			else {
				entry.value = this.collisionHashTable.get(key); // move an entry form the collision hash table to this hash table
				this.collisionHashTable.delete(key);

				if (this.collisionHashTable.size == 0) {
					this.collisionHashTable = null; // delete the collision hash table
				}
			}

			this.size--;
		}
		else {
			if (this.collisionHashTable == null) {
				return;
			}

			final int previousSize = this.collisionHashTable.size;
			this.collisionHashTable.delete(key);

			if (previousSize > this.collisionHashTable.size) {
				this.size--;

				if (this.collisionHashTable.size == 0) {
					this.collisionHashTable = null; // delete the collision hash table
				}
			}
		}
	}

	public V get(final K key) {
		final int index = this.hash(key);
		final Entry<K, V> entry = this.entries[index];

		if (entry == null) {
			return null;
		}

		if (key.equals(entry.key)) { // key == entry.key
			return entry.value;
		}

		if (this.collisionHashTable == null) {
			return null;
		}

		return this.collisionHashTable.get(key);
	}

	public int getSize() {
		return this.size;
	}

	/**
	 * 
	 * @param {*} string
	 * @returns
	 */
	private int hash(final K object) {
		return (int) (Murmur.murmur3(object.toString(), this.seed) % this.entries.length);
	}

	public void set(final K key, final V value) {
		final int index = this.hash(key);
		final Entry<K, V> entry = this.entries[index];

		if (entry == null) {
			this.entries[index] = new Entry<>(key, value);
			this.size++;
			return;
		}

		if (key.equals(entry.key)) { // key == entry.key
			entry.value = value;
			return;
		}

		if (this.collisionHashTable == null) {
			this.collisionHashTable = new UnorderedHashMap2<>(this.startingSize * 2);
			this.collisionHashTable.seed = this.seed + 1;
		}

		this.collisionHashTable.set(key, value);
		this.size++;
	}
}