/**
 * 
 */
package televisionninja.lib.mathutils.unitsutils;

/**
 * @author TelevisionNinja
 *
 */
public class AreaConversion {
	/**
	 * 
	 * @param a
	 * @return
	 * @author TelevisionNinja
	 */
	public static double acreToSqFt(final double a) {
		return a * 43560d;
	}

	/**
	 * 
	 * @param a
	 * @return
	 * @author TelevisionNinja
	 */
	public static double acreToSqYd(final double a) {
		return a * 4840d;
	}

	/**
	 * 
	 * @param ft
	 * @return
	 * @author TelevisionNinja
	 */
	public static double sqFtToAcre(final double ft) {
		return ft / 43560d;
	}

	/**
	 * 
	 * @param yd
	 * @return
	 * @author TelevisionNinja
	 */
	public static double sqYdToAcre(final double yd) {
		return yd / 4840d;
	}
}