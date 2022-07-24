/**
 * 
 */
package televisionninja.lib.datastructures;

import televisionninja.lib.hashes.Murmur;

/**
 * @author TelevisionNinja
 *
 */
public class UnorderedHashMap<K, V> {
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
	private UnorderedHashMap<K, V>[] collisionHashTables;
	private int startingSize;

	@SuppressWarnings("unchecked")
	UnorderedHashMap() {
		this.startingSize = (int) (Math.pow(2, 4));
		this.entries = new Entry[this.startingSize];
		this.collisionHashTables = new UnorderedHashMap[this.startingSize];
	}

	/**
	 * 
	 * @param {Number} startingSize default of 16
	 */
	@SuppressWarnings("unchecked")
	UnorderedHashMap(final int startingSize) {
		if (startingSize <= 0) {
			this.startingSize = (int) (Math.pow(2, 4));
		}
		else {
			this.startingSize = startingSize;
		}

		this.entries = new Entry[this.startingSize];
		this.collisionHashTables = new UnorderedHashMap[this.startingSize];
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		this.size = 0;
		this.entries = new Entry[this.startingSize];
		this.collisionHashTables = new UnorderedHashMap[this.startingSize];
	}

	public void delete(final K key) {
		final int index = this.hash(key);
		final Entry<K, V> entry = this.entries[index];

		if (entry == null) {
			return;
		}

		final UnorderedHashMap<K, V> collisionHashTable = this.collisionHashTables[index];

		if (key.equals(entry.key)) { // key == entry.key | the entry is in this hash map
			if (collisionHashTable == null) {
				this.entries[index] = null; // delete this.entries[index]
			}
			else {
				entry.value = collisionHashTable.get(key); // move an entry form the collision hash table to this hash table
				collisionHashTable.delete(key);

				if (collisionHashTable.size == 0) {
					this.collisionHashTables[index] = null; // delete the collision hash table
				}
			}

			this.size--;
		}
		else {
			if (collisionHashTable == null) {
				return;
			}

			final int previousSize = collisionHashTable.size;
			collisionHashTable.delete(key);

			if (previousSize > collisionHashTable.size) {
				this.size--;

				if (collisionHashTable.size == 0) {
					this.collisionHashTables[index] = null; // delete the collision hash table
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

		final UnorderedHashMap<K, V> collisionHashTable = this.collisionHashTables[index];

		if (collisionHashTable == null) {
			return null;
		}

		return collisionHashTable.get(key);
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

		UnorderedHashMap<K, V> collisionHashTable = this.collisionHashTables[index];

		if (collisionHashTable == null) {
			collisionHashTable = new UnorderedHashMap<>(this.entries.length);
			collisionHashTable.seed = this.seed + 1;
			this.collisionHashTables[index] = collisionHashTable;
		}

		collisionHashTable.set(key, value);
		this.size++;
	}
}
