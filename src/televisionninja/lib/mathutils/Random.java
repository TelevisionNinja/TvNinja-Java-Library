package televisionninja.lib.mathutils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author TelevisionNinja
 *
 */
public class Random {
	/**
	 * Math.random() method
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @author TelevisionNinja
	 */
	public static double randomDouble_1(final double min, final double max) {
		return Math.random() * Math.abs(max - min) + min;
	}

	/**
	 * ThreadLocalRandom.current().nextDouble() method
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @author TelevisionNinja
	 */
	public static double randomDouble_2(final double min, final double max) {
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

	/**
	 * Math.random() method
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @author TelevisionNinja
	 */
	public static int randomInt_1(final int min, final int max) {
		return (int) (Math.random() * Math.abs(max - min) + min);
	}

	/**
	 * ThreadLocalRandom.current().nextInt() method
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @author TelevisionNinja
	 */
	public static int randomInt_2(final int min, final int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	/**
	 * Math.random() method
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @author TelevisionNinja
	 */
	public static long randomLong_1(final long min, final long max) {
		return (long) (Math.random() * Math.abs(max - min) + min);
	}

	/**
	 * ThreadLocalRandom.current().nextLong() method
	 * 
	 * @param min
	 * @param max
	 * @return
	 * @author TelevisionNinja
	 */
	public static long randomLong_2(final long min, final long max) {
		return ThreadLocalRandom.current().nextLong(min, max);
	}
}