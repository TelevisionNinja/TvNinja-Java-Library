/**
 * 
 */
package televisionninja.lib.hashes;

/**
 * @author TelevisionNinja
 *
 */
public class Murmur {
	/**
	 * gets the little endian int from 4 bytes starting at an index
	 * 
	 * @param string
	 * @param index
	 * @return
	 */
	private static int getLittleEndianInt(final String string, final int index) {
		return (string.charAt(index) & 0xff) |
			((string.charAt(index + 1) & 0xff) <<  8) |
			((string.charAt(index + 2) & 0xff) << 16) |
			((string.charAt(index + 3) & 0xff) << 24);
	}

	/**
	 * unsigned 32 bit cannot be represented using java int
	 * thus a long is returned
	 * 
	 * @param string
	 * @param seed
	 * @return
	 */
	public static long murmur3(final String string, final int seed) {
		final int remainder = string.length() & 3;
		final int bytes = string.length() - remainder;
		int hash = seed;
		int i = 0;

		while (i < bytes) {
			final int k = getLittleEndianInt(string, i);

			i += 4;

			hash ^= murmur3Scramble(k);
			hash = Integer.rotateLeft(hash, 13);
			hash = hash * 5 + 0xe6546b64;
		}

		int k = 0;

		switch (remainder) {
			case 3:
				k ^= (string.charAt(i + 2) & 0xff) << 16;
			case 2:
				k ^= (string.charAt(i + 1) & 0xff) << 8;
			case 1:
				k ^= string.charAt(i) & 0xff;
	
				hash ^= murmur3Scramble(k);
		}

		hash ^= string.length();

		hash ^= hash >>> 16;
		hash *= 0x85ebca6b;
		hash ^= hash >>> 13;
		hash *= 0xc2b2ae35;
		hash ^= hash >>> 16;

		return hash & 0xFFFFFFFFL; // make unsigned 32 bit integer
	}

	private static int murmur3Scramble(int k) {
		k *= 0xcc9e2d51;
		k = Integer.rotateLeft(k, 15);
		return k * 0x1b873593;
	}
}
